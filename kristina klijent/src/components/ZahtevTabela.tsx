import React from 'react'
import { Table } from 'semantic-ui-react'
import { ZahtevZaKatalog } from '../model/tipovi'

interface Props {
    zahtevi: ZahtevZaKatalog[],
    selektovan: number,
    onClick: (index: number) => void
}


export default function ZahtevTabela(props: Props) {
    return (
        <Table selectable>
            <Table.Header>
                <Table.Row>
                    <Table.HeaderCell>Broj Kataloga</Table.HeaderCell>
                    <Table.HeaderCell>Ime Kataloga</Table.HeaderCell>
                    <Table.HeaderCell>Zaposleni</Table.HeaderCell>
                    <Table.HeaderCell>Dobavljac</Table.HeaderCell>
                </Table.Row>
            </Table.Header>
            <Table.Body>
                {props.zahtevi.map((element, index) => {
                    return (
                        <Table.Row key={element.brojKataloga} active={index === props.selektovan} onClick={() => {
                            props.onClick(index);
                        }}>
                            <Table.Cell>{element.brojKataloga}</Table.Cell>
                            <Table.Cell>{element.imeKataloga}</Table.Cell>
                            <Table.Cell>{element.zaposleni.imePrezime}</Table.Cell>
                            <Table.Cell>{element.dobavljac.nazivDobavljaca}</Table.Cell>
                        </Table.Row>
                    )
                })}
            </Table.Body>
        </Table>
    )
}
