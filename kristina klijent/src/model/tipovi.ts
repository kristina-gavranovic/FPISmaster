export interface Zaposleni {
    jmbg: string,
    imePrezime: string,

}
export interface RokIsporuke {
    rokId: number,
    brojDana: number
}
export interface Proizvod {
    sifraProizvoda: number,
    nazivProizvoda: string,
    iznosProizvoda: number,
}
export interface Dobavljac {
    pibDobavljaca: string,
    nazivDobavljaca: string,
    emailDobavljaca: string,
    telefonDobavljaca: string,
}
export interface ZahtevZaKatalog {
    brojKataloga: number,
    imeKataloga: string,
    zaposleni: Zaposleni,
    dobavljac: Dobavljac
}
export interface ZahtevDTO {
    brojKataloga: number,
    imeKataloga: string,
    zaposleniId: string,
    dobavljacId: string

}

export interface Narudzbenica {
    brojNarudzbenice: number | undefined,
    ukupnoNar: number,
    rok: RokIsporuke,
    zaposleni: Zaposleni,
    stavke: StavkaNarudzbenice[]

}

export interface StavkaNarudzbenice {
    rbNarudzbenice: number | undefined,
    proizvod: Proizvod,
    kolicina: number,
    narudzbenica: Narudzbenica | undefined,
    opis: string,
    statusAkcije: -1 | 0 | 1
}
export interface NarudzbenicaDTO {
    brojNarudzbenice: number | undefined,
    ukupnoNar: number,
    rokId: number,
    zaposleniId: string,
    stavke: StavkaNarudzbeniceDTO[]

}

export interface StavkaNarudzbeniceDTO {
    id: number | undefined,
    proizvodId: number,
    kolicina: number,
    narudzbenicaId: number | undefined,
    opisNarudzbenice: string,
    statusAkcije: -1 | 0 | 1
}