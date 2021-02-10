import React, { useEffect, useState } from 'react'
import { Button, DropdownItemProps, Form, Grid, Modal, Table } from 'semantic-ui-react'
import { Narudzbenica, NarudzbenicaDTO, Proizvod, RokIsporuke, StavkaNarudzbenice, Zaposleni } from '../model/tipovi'

interface Props {
    open: boolean,
    close: () => void,
    narudzbenica?: Narudzbenica,
    zaposleni: Zaposleni[],
    rokovi: RokIsporuke[],
    proizvodi: Proizvod[],
    onSubmit: (nar: Partial<NarudzbenicaDTO>) => void,
}

export default function NarudzbenicaForma(props: Props) {

    const [zaposleni, setZaposleni] = useState<Zaposleni | undefined>(undefined)
    const [zaposleniGreska, setZaposleniGreska] = useState('')

    const [proizvod, setProizvod] = useState<Proizvod | undefined>(undefined);
    const [proizvodGreska, setProizvodGreska] = useState('');

    const [rok, setRok] = useState<RokIsporuke | undefined>(undefined);
    const [rokGreska, setRokGreska] = useState('');

    const [stavke, setStavke] = useState<StavkaNarudzbenice[]>([]);
    const [selectedStavka, setSelectedStavka] = useState(-1);

    const [kolicina, setKolicina] = useState(0);
    const [kolicinaGreska, setKolicinaGreska] = useState('')

    const [opis, setOpis] = useState('');
    const [opisGreska, setOpisGreska] = useState('');

    const [ukupnoNar, setUkupnoNar] = useState(0);
    const [ukupnoGreska, setUkupnoGreska] = useState('')

    useEffect(() => {

        setKolicina(0);
        setKolicinaGreska('');
        setOpis('');
        setOpisGreska('');
        setProizvod(props.proizvodi[0]);
        setProizvodGreska('');

        setRok(props.narudzbenica?.rok || props.rokovi[0]);
        setRokGreska('')
        setUkupnoNar(props.narudzbenica?.ukupnoNar || 0);
        setUkupnoGreska('');
        setZaposleni(props.narudzbenica?.zaposleni || props.zaposleni[0]);
        setZaposleniGreska('');
        setStavke(props.narudzbenica?.stavke || [])

    }, [props.narudzbenica])

    useEffect(() => {

        if (selectedStavka === -1) {
            setKolicina(0);
            setKolicinaGreska('');
            setOpis('');
            setOpisGreska('');
            setProizvod(props.proizvodi[0]);
            setProizvodGreska('');
        } else {
            const stavka = stavke[selectedStavka];
            setKolicina(stavka.kolicina);
            setKolicinaGreska('');
            setOpis(stavka.opis);
            setOpisGreska('');
            setProizvod(stavka.proizvod);
            setProizvodGreska('');
        }

    }, [selectedStavka]);
    const sacuvajStavku = () => {
        if (kolicina <= 0) {
            setKolicinaGreska('kolicina mora biti veca od nule');
            return;
        }
        setKolicinaGreska('');
        if (!proizvod) {
            setProizvodGreska('Proizvod je obavezan');
            return;
        }
        setProizvodGreska('');
        if (opis === '') {
            setOpisGreska('Opis je obavezan');
            return;
        }
        setOpisGreska('')
        if (selectedStavka === -1) {
            setStavke(prev => {
                return [...prev, {
                    kolicina: kolicina,
                    opis: opis,
                    narudzbenica: props.narudzbenica,
                    proizvod: proizvod!,
                    statusAkcije: 0,
                    rbNarudzbenice: undefined
                }]
            })
        } else {
            setStavke(prev => {
                return prev.map(element => {
                    if (element !== stavke[selectedStavka]) {
                        return element;
                    }
                    return {
                        ...element,
                        kolicina: kolicina,
                        narudzbenica: props.narudzbenica,
                        opis: opis,
                        proizvod: proizvod!,

                    }
                })
            })
            setSelectedStavka(-1);
        }
    }
    const obrisiStavku = () => {
        const stavka = stavke[selectedStavka];
        if (stavka.statusAkcije === 0) {
            setStavke(prev => {
                return prev.filter(element => element !== stavka);
            })
            setSelectedStavka(-1);
            return;
        }
        if (stavka.statusAkcije === 1) {
            setStavke(prev => {
                return prev.map(element => {
                    if (element === stavka) {
                        return { ...element, statusAkcije: -1 }
                    }
                    return element;
                })
            })
        }
        setSelectedStavka(-1);
    }
    return (
        <Modal open={props.open} onClose={props.close}>
            <Grid padded>
                <Grid.Row columns='16'>
                    <Grid.Column width='6'>
                        <Form>
                            {props.narudzbenica && <Form.Input readOnly label='Broj narudzbenice' value={props.narudzbenica?.brojNarudzbenice} />}
                            <Form.Input label='Ukupno naruceno' type='number' value={ukupnoNar} error={ukupnoGreska !== '' && ukupnoGreska} onChange={(e) => {
                                const value = e.currentTarget.value;
                                setUkupnoNar(parseFloat(value));;
                            }} />
                            <Form.Dropdown selection value={rok?.rokId} error={rokGreska !== '' && rokGreska} label='Rok' options={
                                props.rokovi.map((element): DropdownItemProps => {
                                    return {
                                        value: element.rokId,
                                        text: element.brojDana,
                                        onClick: () => { setRok(element) }
                                    }
                                })
                            } />
                            <Form.Dropdown selection error={zaposleniGreska !== '' && zaposleniGreska} value={zaposleni?.jmbg} label='Zaposleni' options={
                                props.zaposleni.map((element): DropdownItemProps => {
                                    return {
                                        value: element.jmbg,
                                        text: element.imePrezime,
                                        onClick: () => { setZaposleni(element) }
                                    }
                                })
                            } />
                        </Form>
                    </Grid.Column>
                    <Grid.Column width='4'>
                        <Table selectable>
                            <Table.Header>
                                <Table.Row>
                                    <Table.HeaderCell>Proizvod</Table.HeaderCell>
                                    <Table.HeaderCell>Kolicina</Table.HeaderCell>
                                </Table.Row>
                            </Table.Header>
                            <Table.Body>
                                {stavke.filter(element => element.statusAkcije !== -1).map((element, index) => {
                                    return <Table.Row active={index === selectedStavka} onClick={() => {
                                        if (index === selectedStavka) {
                                            setSelectedStavka(-1)
                                        } else {
                                            setSelectedStavka(index)
                                        }
                                    }}>
                                        <Table.Cell>{element.proizvod.nazivProizvoda}</Table.Cell>
                                        <Table.Cell>{element.kolicina}</Table.Cell>
                                    </Table.Row>
                                })}
                            </Table.Body>
                        </Table>
                    </Grid.Column>
                    <Grid.Column width='6'>
                        <Form>
                            <Form.Input type='number' label='Kolicina' error={kolicinaGreska !== '' && kolicinaGreska} value={kolicina} onChange={(e) => {
                                const value = e.currentTarget.value;
                                setKolicina(parseFloat(value));
                            }} />
                            <Form.Dropdown selection error={proizvodGreska !== '' && proizvodGreska} value={proizvod?.sifraProizvoda} label='Proizvod' options={
                                props.proizvodi.map((element): DropdownItemProps => {
                                    return {
                                        value: element.sifraProizvoda,
                                        text: element.nazivProizvoda,
                                        onClick: () => { setProizvod(element) }
                                    }
                                })
                            } />
                            <Form.TextArea error={opisGreska !== '' && opisGreska} label='Opis' value={opis} onChange={(e) => {
                                const value = e.currentTarget.value;
                                setOpis(value);
                            }} />
                            <Form.Button primary fluid onClick={sacuvajStavku}>Sacuvaj stavku</Form.Button>
                            {selectedStavka !== -1 && (
                                <Form.Button negative onClick={obrisiStavku} fluid>Obrisi</Form.Button>
                            )}
                        </Form>
                    </Grid.Column>
                </Grid.Row>
                <Grid.Row>
                    <Button fluid onClick={() => {
                        if (ukupnoNar <= 0) {
                            setUkupnoGreska('Ukupna kolicina mora biti veca od nule');
                            return;
                        }
                        setUkupnoGreska('');
                        if (!rok) {
                            setRokGreska('Rok isporuke je obavezan');
                            return
                        }
                        setRokGreska('');
                        if (!zaposleni) {
                            setZaposleniGreska('Zaposleni je obavezan')
                            return;
                        }
                        setZaposleniGreska('');
                        const narudzbenicaDTO: NarudzbenicaDTO = {
                            brojNarudzbenice: props.narudzbenica?.brojNarudzbenice,
                            rokId: rok.rokId,
                            zaposleniId: zaposleni.jmbg,
                            ukupnoNar: ukupnoNar,
                            stavke: stavke.map(element => {
                                return {
                                    id: element.rbNarudzbenice,
                                    kolicina: element.kolicina,
                                    narudzbenicaId: props.narudzbenica?.brojNarudzbenice,
                                    opisNarudzbenice: element.opis,
                                    proizvodId: element.proizvod.sifraProizvoda,
                                    statusAkcije: element.statusAkcije
                                }
                            })
                        };

                        props.onSubmit(narudzbenicaDTO);

                    }}>Sacuvaj narudzbenicu</Button>
                </Grid.Row>
            </Grid>
        </Modal>
    )
}
