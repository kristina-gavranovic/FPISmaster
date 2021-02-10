import React, { useEffect, useState } from 'react'
import { DropdownItemProps, Form } from 'semantic-ui-react'
import { Dobavljac, ZahtevDTO, ZahtevZaKatalog, Zaposleni } from '../model/tipovi'
interface Props {
    zahtev?: ZahtevZaKatalog,
    onKreiraj: (zahtev: Partial<ZahtevDTO>) => void,
    onIzmeni: (zahtev: Partial<ZahtevDTO>) => void,
    onObrisi: (zahtev: Partial<ZahtevDTO>) => void,
    zaposleni: Zaposleni[],
    dobavljaci: Dobavljac[]
}
export default function ZahtevForma(props: Props) {
    const [imeKatalog, setImeKatalog] = useState('')
    const [imeGreska, setImeGreska] = useState('');

    const [zaposleni, setZaposleni] = useState<Zaposleni | undefined>(undefined);
    const [zaposleniGreska, setZaposleniGreska] = useState('');

    const [dobavljac, setDobavljac] = useState<Dobavljac | undefined>(undefined);
    const [dobavljacGreska, setDobavljacGreska] = useState('');

    const potvrdiFormu = (callback: ((zahtev: Partial<ZahtevDTO>) => void)) => {
        if (imeKatalog === '') {
            setImeGreska('Ime je obavezno');
            return;
        }
        setImeGreska('');
        if (!zaposleni) {
            setZaposleniGreska('Zaposleni je obavezan')
            return;
        }
        setZaposleniGreska('')
        if (!dobavljac) {
            setDobavljacGreska('Dobavljac je obavezan')
            return;
        }
        setDobavljacGreska('')
        callback({
            brojKataloga: props.zahtev?.brojKataloga,
            imeKataloga: imeKatalog,
            zaposleniId: zaposleni.jmbg,
            dobavljacId: dobavljac.pibDobavljaca
        })
    }

    useEffect(() => {
        setImeKatalog(props.zahtev?.imeKataloga || '');
        setDobavljac(props.zahtev?.dobavljac);
        setZaposleni(props.zahtev?.zaposleni);
        setDobavljacGreska('');
        setZaposleniGreska('');
        setImeGreska('');

    }, [props.zahtev])

    return (
        <Form>
            {
                props.zahtev && (
                    <Form.Input label='Broj kataloga' readOnly value={props.zahtev.brojKataloga} />
                )
            }
            <Form.Input label='Ime kataloga' error={imeGreska !== '' && imeGreska} value={imeKatalog} onChange={(e) => {
                const value = e.currentTarget.value;
                setImeKatalog(value);
            }} />
            <Form.Dropdown value={dobavljac?.pibDobavljaca} selection label='Dobavljac' error={dobavljacGreska !== '' && imeGreska}
                options={props.dobavljaci.map((element): DropdownItemProps => {
                    return {
                        active: element === dobavljac,
                        value: element?.pibDobavljaca,
                        text: element?.nazivDobavljaca,
                        onClick: () => {
                            setDobavljac(element)
                        }

                    }
                })}

            />
            <Form.Dropdown value={zaposleni?.jmbg} selection label='Zaposleni' error={zaposleniGreska !== '' && zaposleniGreska}
                options={props.zaposleni.map((element): DropdownItemProps => {
                    return {
                        active: element === zaposleni,
                        value: element?.jmbg,
                        text: element?.imePrezime,
                        onClick: () => {
                            setZaposleni(element)
                        }

                    }
                })}

            />
            {
                props.zahtev ? (
                    <>
                        <Form.Button fluid positive onClick={() => {
                            potvrdiFormu(props.onIzmeni);
                        }}>Izmeni</Form.Button>
                        <Form.Button fluid negative onClick={() => {
                            props.onObrisi({
                                brojKataloga: props.zahtev?.brojKataloga
                            })
                        }}>Obrisi</Form.Button>
                    </>
                ) : (
                        <Form.Button primary fluid onClick={() => {
                            potvrdiFormu(props.onKreiraj);
                        }}>Kreiraj</Form.Button>
                    )
            }
        </Form>
    )
}
