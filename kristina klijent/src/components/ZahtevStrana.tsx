import axios from 'axios'
import React, { useEffect, useState } from 'react'
import { Grid, Input } from 'semantic-ui-react'
import { Dobavljac, ZahtevDTO, ZahtevZaKatalog, Zaposleni } from '../model/tipovi'
import ZahtevForma from './ZahtevForma'
import ZahtevTabela from './ZahtevTabela'

export default function ZahtevStrana() {

    const [zahtevi, setZahtevi] = useState<ZahtevZaKatalog[]>([]);
    const [izabrani, setIzabrani] = useState(-1);
    const [pretraga, setPretraga] = useState('');

    const [dobavljaci, setDobavljaci] = useState<Dobavljac[]>([]);
    const [zaposleni, setZaposleni] = useState<Zaposleni[]>([]);
    useEffect(() => {
        const funkcija = async () => {

            const dobavljaciData = (await axios.get('http://localhost:7000/dobavljac')).data;
            const zaposlenData = (await axios.get('http://localhost:7000/zaposleni')).data;
            const zahteviData = (await axios.get('http://localhost:7000/zahtev')).data as ZahtevDTO[];
            setDobavljaci(dobavljaciData);
            setZaposleni(zaposlenData);
            setZahtevi(zahteviData.map(element => {
                return {
                    brojKataloga: element.brojKataloga,
                    dobavljac: dobavljaciData.find((dob: Dobavljac) => dob.pibDobavljaca === element.dobavljacId)!,
                    zaposleni: zaposlenData.find((zap: Zaposleni) => zap.jmbg === element.zaposleniId)!,
                    imeKataloga: element.imeKataloga
                }
            }))

        }
        funkcija();
    }, [])

    const selektuj = (index: number) => {
        if (izabrani === index) {
            setIzabrani(-1);
        } else {
            setIzabrani(index);
        }
    }
    const filtrirajZahteve = () => {
        return zahtevi.filter(element => {
            return element.imeKataloga.includes(pretraga)
        })
    }
    const onKreiraj = async (zahtev: Partial<ZahtevDTO>) => {
        const noviZahtev = (await axios.post('http://localhost:7000/zahtev', zahtev)).data as ZahtevDTO;
        setZahtevi(prev => {

            return [...prev, {
                brojKataloga: noviZahtev.brojKataloga,
                dobavljac: dobavljaci.find((dob: Dobavljac) => dob.pibDobavljaca === noviZahtev.dobavljacId)!,
                zaposleni: zaposleni.find((zap: Zaposleni) => zap.jmbg === noviZahtev.zaposleniId)!,
                imeKataloga: noviZahtev.imeKataloga
            }]

        });

    }
    const onIzmeni = async (zahtev: Partial<ZahtevDTO>) => {
        const noviZahtev = (await axios.patch('http://localhost:7000/zahtev/' + zahtev.brojKataloga, zahtev)).data as ZahtevDTO;
        setZahtevi(prev => {
            return prev.map(element => {
                if (element.brojKataloga !== noviZahtev.brojKataloga)
                    return element;
                return {
                    brojKataloga: noviZahtev.brojKataloga,
                    dobavljac: dobavljaci.find((dob: Dobavljac) => dob.pibDobavljaca === noviZahtev.dobavljacId)!,
                    zaposleni: zaposleni.find((zap: Zaposleni) => zap.jmbg === noviZahtev.zaposleniId)!,
                    imeKataloga: noviZahtev.imeKataloga
                }
            })
        });
        setIzabrani(-1);
    }
    const onObrisi = async (zahtev: Partial<ZahtevDTO>) => {
        const rezultat = (await axios.delete('http://localhost:7000/zahtev/' + zahtev.brojKataloga));
        if (rezultat.status === 200) {
            setZahtevi(prev => {
                return prev.filter(element => element.brojKataloga !== zahtev.brojKataloga);
            })
        }
        setIzabrani(-1);

    }
    return (
        <Grid padded>
            <Grid.Row>
                <Input label='Filtriraj zahteve po imenu' value={pretraga} onChange={(e) => {
                    const value = e.currentTarget.value;
                    setPretraga(value);
                }} />
            </Grid.Row>
            <Grid.Row columns='16'>
                <Grid.Column width='8'>
                    <ZahtevTabela
                        onClick={selektuj}
                        selektovan={izabrani}
                        zahtevi={filtrirajZahteve()}
                    />
                </Grid.Column>
                <Grid.Column width='8'>
                    <ZahtevForma
                        zahtev={izabrani == -1 ? undefined : zahtevi[izabrani]}
                        onIzmeni={onIzmeni}
                        onKreiraj={onKreiraj}
                        onObrisi={onObrisi}
                        zaposleni={zaposleni}
                        dobavljaci={dobavljaci}
                    />
                </Grid.Column>
            </Grid.Row>
        </Grid>
    )
}
