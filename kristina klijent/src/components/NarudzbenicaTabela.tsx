import React from 'react'
import { Table } from 'semantic-ui-react'
import { Narudzbenica } from '../model/tipovi'
interface Props {
    narudzbenice: Narudzbenica[],
    selected: number,
    onClick: (index: number) => void;
}
export default function NarudzbenicaTabela(props: Props) {
    return (
        <Table selectable>
            <Table.Header>
                <Table.Row >
                    <Table.HeaderCell>Broj narudzbenice</Table.HeaderCell>
                    <Table.HeaderCell>Ukupno naruceno</Table.HeaderCell>
                    <Table.HeaderCell>Zaposleni</Table.HeaderCell>
                    <Table.HeaderCell>Rok isporuke</Table.HeaderCell>
                    <Table.HeaderCell>Broj stavki</Table.HeaderCell>
                </Table.Row>
            </Table.Header>
            <Table.Body>
                {props.narudzbenice.map((element, index) => {
                    return (
                        <Table.Row key={element.brojNarudzbenice} active={index === props.selected} onClick={() => { props.onClick(index) }}>
                            <Table.Cell>{element.brojNarudzbenice}</Table.Cell>
                            <Table.Cell>{element.ukupnoNar}</Table.Cell>
                            <Table.Cell>{element.zaposleni.imePrezime}</Table.Cell>
                            <Table.Cell>{element.rok.brojDana} dana</Table.Cell>
                            <Table.Cell>{element.stavke.length}</Table.Cell>
                        </Table.Row>
                    )
                })}
            </Table.Body>
        </Table>
    )
}
