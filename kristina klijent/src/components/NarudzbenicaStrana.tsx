import axios from 'axios';
import React, { useEffect, useState } from 'react';
import { Button, Grid, Input } from 'semantic-ui-react';
import { Narudzbenica, NarudzbenicaDTO, Proizvod, RokIsporuke, Zaposleni } from '../model/tipovi';
import NarudzbenicaForma from './NarudzbenicaForma';
import NarudzbenicaTabela from './NarudzbenicaTabela';

export default function NarudzbenicaStrana() {
    const [zaposleni, setZaposleni] = useState<Zaposleni[]>([]);
    const [rokovi, setRokovi] = useState<RokIsporuke[]>([]);
    const [proizvodi, setProizvodi] = useState<Proizvod[]>([])
    const [narudzbenice, setNarudzbenice] = useState<Narudzbenica[]>([])
    const [selected, setSelected] = useState(-1);
    const [search, setSearch] = useState('')
    const [openForm, setOpenForm] = useState(false);

    useEffect(() => {
        const funkcija = async () => {
            const zaposlenData = (await axios.get('http://localhost:7000/zaposleni')).data
            setZaposleni(zaposlenData);
            const rokoviData = (await axios.get('http://localhost:7000/rok')).data
            setRokovi(rokoviData);
            const proizvodiData = (await axios.get('http://localhost:7000/proizvod')).data
            setProizvodi(proizvodiData);
            const narudzbeniceDTO = (await axios.get('http://localhost:7000/narudzbenica')).data

            setNarudzbenice(narudzbeniceDTO.map((element: any): Narudzbenica => {
                const narudz = {
                    ...element,
                    brojNarudzbenice: element.brojNarudzbenice,
                    ukupnoNar: element.ukupnoNar,
                    rok: rokoviData.find((rok: any) => (rok.rokId === element.rokId))!,
                    zaposleni: zaposlenData.find((zap: any) => zap.jmbg === element.zaposleniId)!,

                };

                narudz.stavke = element.stavke.map((stavka: any) => {
                    return {
                        kolicina: stavka.kolicina,
                        narudzbenica: narudz,
                        opis: stavka.opisNarudzbenice,
                        proizvod: proizvodiData.find((proizvod: any) => proizvod.sifraProizvoda === stavka.proizvodId),
                        rbNarudzbenice: stavka.id,
                        statusAkcije: 1
                    }
                })
                return narudz;

            }))
        }
        funkcija();

    }, [])

    const select = (index: number) => {
        if (selected === index) {
            setSelected(-1);
        } else {
            setSelected(index);
        }
    }
    const filtriraj = () => {
        return narudzbenice.filter(element => element.zaposleni.imePrezime.includes(search))
    }
    const obrisi = async () => {
        const status = (await axios.delete('http://localhost:7000/narudzbenica/' + narudzbenice[selected].brojNarudzbenice)).status;
        if (status === 200) {
            setNarudzbenice(prev => {
                return prev.filter(element => element !== narudzbenice[selected]);
            })
        }
    }
    const onSubmit = async (narudz: Partial<NarudzbenicaDTO>) => {
        if (selected !== -1) {
            const novaNar = (await axios.patch('http://localhost:7000/narudzbenica/' + narudzbenice[selected].brojNarudzbenice, narudz)).data as NarudzbenicaDTO;
            setNarudzbenice(prev => {
                return prev.map((element, index) => {
                    if (index !== selected) {
                        return element;
                    }
                    const nar: Narudzbenica = {
                        brojNarudzbenice: novaNar.brojNarudzbenice,
                        rok: rokovi.find(rok => rok.rokId === novaNar.rokId)!,
                        ukupnoNar: novaNar.ukupnoNar,
                        zaposleni: zaposleni.find(zap => zap.jmbg === novaNar.zaposleniId)!,
                        stavke: []
                    };
                    nar.stavke = novaNar.stavke.map(stavka => {
                        return {
                            kolicina: stavka.kolicina,
                            narudzbenica: nar,
                            opis: stavka.opisNarudzbenice,
                            proizvod: proizvodi.find(pr => pr.sifraProizvoda === stavka.proizvodId)!,
                            statusAkcije: 1,
                            rbNarudzbenice: stavka.id
                        }
                    })
                    return nar;
                })
            })
        } else {
            const novaNar = (await axios.post('http://localhost:7000/narudzbenica', narudz)).data as NarudzbenicaDTO;
            setNarudzbenice(prev => {
                const nar: Narudzbenica = {
                    brojNarudzbenice: novaNar.brojNarudzbenice,
                    rok: rokovi.find(rok => rok.rokId === novaNar.rokId)!,
                    ukupnoNar: novaNar.ukupnoNar,
                    zaposleni: zaposleni.find(zap => zap.jmbg === novaNar.zaposleniId)!,
                    stavke: []
                };
                nar.stavke = novaNar.stavke.map(stavka => {
                    return {
                        kolicina: stavka.kolicina,
                        narudzbenica: nar,
                        opis: stavka.opisNarudzbenice,
                        proizvod: proizvodi.find(pr => pr.sifraProizvoda === stavka.proizvodId)!,
                        statusAkcije: 1,
                        rbNarudzbenice: stavka.id
                    }
                })
                return [...prev, nar];
            })
        }
    }
    return (
        <>
            <NarudzbenicaForma
                zaposleni={zaposleni}
                proizvodi={proizvodi}
                rokovi={rokovi}
                onSubmit={onSubmit}
                close={() => { setOpenForm(false) }}
                open={openForm}
                narudzbenica={selected === -1 ? undefined : narudzbenice[selected]}
            />
            <Grid padded>
                <Grid.Row>
                    <Input label='Pretrazi' value={search} onChange={(e) => {
                        const value = e.currentTarget.value;
                        setSearch(value);
                    }} />
                    {
                        selected !== -1 ? (
                            <>
                                <Button positive onClick={() => {
                                    setOpenForm(true);
                                }}>Izmeni</Button>
                                <Button negative onClick={obrisi} >Obrisi</Button>
                            </>
                        ) : (
                                <Button primary onClick={() => {
                                    setOpenForm(true);
                                }}>Kreiraj</Button>
                            )
                    }
                </Grid.Row>
                <Grid.Row columns='16'>
                    <Grid.Column width='16'>
                        <NarudzbenicaTabela
                            narudzbenice={filtriraj()}
                            onClick={select}
                            selected={selected}
                        />
                    </Grid.Column>
                </Grid.Row>
            </Grid>
        </>
    )
}
