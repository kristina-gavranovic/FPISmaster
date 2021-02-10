import React from 'react'
import { Link, RouteComponentProps, withRouter } from 'react-router-dom'
import { Menu, Segment } from 'semantic-ui-react'

export default withRouter(function Navbar(props: RouteComponentProps) {
    return (
        <Segment>
            <Menu fluid secondary>
                <Menu.Item active={props.location.pathname === '/'} as={Link} to='/'>Zahtev za katalog</Menu.Item>
                <Menu.Item active={props.location.pathname === '/narudzbenica'} as={Link} to='/narudzbenica'>Narudzbenica</Menu.Item>
            </Menu>

        </Segment>
    )
})
