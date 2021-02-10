import React from 'react';
import './App.css';
import Navbar from './components/Navbar';
import { Route, Switch } from 'react-router';
import ZahtevStrana from './components/ZahtevStrana';
import NarudzbenicaStrana from './components/NarudzbenicaStrana';

function App() {
  return (
    <>
      <Navbar />
      <Switch>
        <Route path='/narudzbenica'>
          <NarudzbenicaStrana />
        </Route>
        <Route path='/'>
          <ZahtevStrana />
        </Route>
      </Switch>

    </>
  );
}

export default App;
