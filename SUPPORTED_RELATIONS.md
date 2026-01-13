# 🔗 Supported Auto-Relations
This list is automatically generated from the FINT Metamodel.
It is grouped by **FINT Component** (`domain.package`).

## 📦 Component: administrasjon.fullmakt
### ⚡ Trigger: `Fullmakt`
When `Fullmakt` is updated, it updates the following targets:

| Target Resource | Relations to Update (on Target) |
| :--- | :--- |
| `administrasjon.kodeverk.Kontodimensjon` | `fullmakt` |

### ⚡ Trigger: `Rolle`
When `Rolle` is updated, it updates the following targets:

| Target Resource | Relations to Update (on Target) |
| :--- | :--- |
| `administrasjon.fullmakt.Fullmakt` | `rolle` |

---
## 📦 Component: administrasjon.kodeverk
### ⚡ Trigger: `Ansvar`
When `Ansvar` is updated, it updates the following targets:

| Target Resource | Relations to Update (on Target) |
| :--- | :--- |
| `administrasjon.kodeverk.Ansvar` | `overordnet` |
| `administrasjon.organisasjon.Organisasjonselement` | `ansvar` |

### ⚡ Trigger: `Funksjon`
When `Funksjon` is updated, it updates the following targets:

| Target Resource | Relations to Update (on Target) |
| :--- | :--- |
| `administrasjon.kodeverk.Funksjon` | `overordnet` |

### ⚡ Trigger: `Prosjekt`
When `Prosjekt` is updated, it updates the following targets:

| Target Resource | Relations to Update (on Target) |
| :--- | :--- |
| `administrasjon.kodeverk.Prosjektart` | `prosjekt` |

### ⚡ Trigger: `Prosjektart`
When `Prosjektart` is updated, it updates the following targets:

| Target Resource | Relations to Update (on Target) |
| :--- | :--- |
| `administrasjon.kodeverk.Prosjektart` | `overordnet` |

---
## 📦 Component: administrasjon.organisasjon
### ⚡ Trigger: `Arbeidslokasjon`
When `Arbeidslokasjon` is updated, it updates the following targets:

| Target Resource | Relations to Update (on Target) |
| :--- | :--- |
| `administrasjon.personal.Arbeidsforhold` | `arbeidslokasjon` |

### ⚡ Trigger: `Organisasjonselement`
When `Organisasjonselement` is updated, it updates the following targets:

| Target Resource | Relations to Update (on Target) |
| :--- | :--- |
| `administrasjon.kodeverk.Ansvar` | `organisasjonselement` |
| `administrasjon.personal.Arbeidsforhold` | `arbeidssted` |

---
## 📦 Component: administrasjon.personal
### ⚡ Trigger: `Arbeidsforhold`
When `Arbeidsforhold` is updated, it updates the following targets:

| Target Resource | Relations to Update (on Target) |
| :--- | :--- |
| `administrasjon.personal.Fastlonn` | `arbeidsforhold` |
| `administrasjon.personal.Fasttillegg` | `arbeidsforhold` |
| `administrasjon.personal.Fravar` | `arbeidsforhold` |
| `administrasjon.personal.Variabellonn` | `arbeidsforhold` |

### ⚡ Trigger: `Fravar`
When `Fravar` is updated, it updates the following targets:

| Target Resource | Relations to Update (on Target) |
| :--- | :--- |
| `administrasjon.personal.Arbeidsforhold` | `fravar` |

### ⚡ Trigger: `Personalressurs`
When `Personalressurs` is updated, it updates the following targets:

| Target Resource | Relations to Update (on Target) |
| :--- | :--- |
| `administrasjon.personal.Arbeidsforhold` | `personalleder`, `personalressurs` |
| `administrasjon.fullmakt.Fullmakt` | `fullmektig`, `stedfortreder` |
| `administrasjon.organisasjon.Organisasjonselement` | `leder` |

---
## 📦 Component: arkiv.noark
### ⚡ Trigger: `Arkivdel`
When `Arkivdel` is updated, it updates the following targets:

| Target Resource | Relations to Update (on Target) |
| :--- | :--- |
| `arkiv.noark.Klassifikasjonssystem` | `arkivdel` |
| `arkiv.noark.Mappe` | `arkivdel` |
| `arkiv.noark.Registrering` | `arkivdel` |

### ⚡ Trigger: `Arkivressurs`
When `Arkivressurs` is updated, it updates the following targets:

| Target Resource | Relations to Update (on Target) |
| :--- | :--- |
| `arkiv.noark.Autorisasjon` | `arkivressurs` |
| `arkiv.noark.Tilgang` | `arkivressurs` |

### ⚡ Trigger: `Autorisasjon`
When `Autorisasjon` is updated, it updates the following targets:

| Target Resource | Relations to Update (on Target) |
| :--- | :--- |
| `arkiv.noark.Arkivressurs` | `autorisasjon` |

### ⚡ Trigger: `Klassifikasjonssystem`
When `Klassifikasjonssystem` is updated, it updates the following targets:

| Target Resource | Relations to Update (on Target) |
| :--- | :--- |
| `arkiv.noark.Arkivdel` | `klassifikasjonssystem` |

### ⚡ Trigger: `Tilgang`
When `Tilgang` is updated, it updates the following targets:

| Target Resource | Relations to Update (on Target) |
| :--- | :--- |
| `arkiv.noark.Arkivressurs` | `tilgang` |

---
## 📦 Component: okonomi.faktura
### ⚡ Trigger: `Fakturagrunnlag`
When `Fakturagrunnlag` is updated, it updates the following targets:

| Target Resource | Relations to Update (on Target) |
| :--- | :--- |
| `okonomi.faktura.Faktura` | `fakturagrunnlag` |

### ⚡ Trigger: `Fakturautsteder`
When `Fakturautsteder` is updated, it updates the following targets:

| Target Resource | Relations to Update (on Target) |
| :--- | :--- |
| `okonomi.faktura.Fakturagrunnlag` | `fakturautsteder` |
| `okonomi.kodeverk.Vare` | `fakturautsteder` |

---
## 📦 Component: okonomi.regnskap
### ⚡ Trigger: `Transaksjon`
When `Transaksjon` is updated, it updates the following targets:

| Target Resource | Relations to Update (on Target) |
| :--- | :--- |
| `okonomi.regnskap.Postering` | `transaksjon` |

---
## 📦 Component: personvern.samtykke
### ⚡ Trigger: `Behandling`
When `Behandling` is updated, it updates the following targets:

| Target Resource | Relations to Update (on Target) |
| :--- | :--- |
| `personvern.samtykke.Samtykke` | `behandling` |

### ⚡ Trigger: `Tjeneste`
When `Tjeneste` is updated, it updates the following targets:

| Target Resource | Relations to Update (on Target) |
| :--- | :--- |
| `personvern.samtykke.Behandling` | `tjeneste` |

---
## 📦 Component: ressurs.datautstyr
### ⚡ Trigger: `DigitalEnhet`
When `DigitalEnhet` is updated, it updates the following targets:

| Target Resource | Relations to Update (on Target) |
| :--- | :--- |
| `ressurs.datautstyr.Enhetsgruppemedlemskap` | `digitalEnhet` |

### ⚡ Trigger: `Enhetsgruppe`
When `Enhetsgruppe` is updated, it updates the following targets:

| Target Resource | Relations to Update (on Target) |
| :--- | :--- |
| `ressurs.datautstyr.Enhetsgruppemedlemskap` | `enhetsgruppe` |

---
## 📦 Component: ressurs.eiendel
### ⚡ Trigger: `Applikasjon`
When `Applikasjon` is updated, it updates the following targets:

| Target Resource | Relations to Update (on Target) |
| :--- | :--- |
| `ressurs.eiendel.Applikasjonsressurs` | `applikasjon` |

### ⚡ Trigger: `Applikasjonsressurs`
When `Applikasjonsressurs` is updated, it updates the following targets:

| Target Resource | Relations to Update (on Target) |
| :--- | :--- |
| `ressurs.eiendel.Applikasjonsressurstilgjengelighet` | `ressurs` |

---
## 📦 Component: ressurs.tilgang
### ⚡ Trigger: `Identitet`
When `Identitet` is updated, it updates the following targets:

| Target Resource | Relations to Update (on Target) |
| :--- | :--- |
| `ressurs.tilgang.Rettighet` | `identitet` |

### ⚡ Trigger: `Rettighet`
When `Rettighet` is updated, it updates the following targets:

| Target Resource | Relations to Update (on Target) |
| :--- | :--- |
| `ressurs.tilgang.Identitet` | `rettighet` |

---
## 📦 Component: utdanning.elev
### ⚡ Trigger: `Basisgruppe`
When `Basisgruppe` is updated, it updates the following targets:

| Target Resource | Relations to Update (on Target) |
| :--- | :--- |
| `utdanning.elev.Basisgruppemedlemskap` | `basisgruppe` |
| `utdanning.elev.Elevforhold` | `basisgruppe` |
| `utdanning.elev.Kontaktlarergruppe` | `basisgruppe` |
| `utdanning.elev.Undervisningsforhold` | `basisgruppe` |

### ⚡ Trigger: `Elev`
When `Elev` is updated, it updates the following targets:

| Target Resource | Relations to Update (on Target) |
| :--- | :--- |
| `utdanning.elev.Elevforhold` | `elev` |

### ⚡ Trigger: `Elevforhold`
When `Elevforhold` is updated, it updates the following targets:

| Target Resource | Relations to Update (on Target) |
| :--- | :--- |
| `utdanning.elev.Basisgruppe` | `elevforhold` |
| `utdanning.elev.Basisgruppemedlemskap` | `elevforhold` |
| `utdanning.vurdering.Eksamensgruppe` | `elevforhold` |
| `utdanning.vurdering.Eksamensgruppemedlemskap` | `elevforhold` |
| `utdanning.elev.Elevtilrettelegging` | `elev` |
| `utdanning.vurdering.Fravar` | `elevforhold` |
| `utdanning.vurdering.Fravarsoversikt` | `elevforhold` |
| `utdanning.vurdering.Halvarsfagvurdering` | `elevforhold` |
| `utdanning.vurdering.Halvarsordensvurdering` | `elevforhold` |
| `utdanning.elev.Kontaktlarergruppe` | `elevforhold` |
| `utdanning.elev.Kontaktlarergruppemedlemskap` | `elevforhold` |
| `utdanning.elev.Persongruppemedlemskap` | `elevforhold` |
| `utdanning.utdanningsprogram.Programomrademedlemskap` | `elevforhold` |
| `utdanning.vurdering.Sluttfagvurdering` | `elevforhold` |
| `utdanning.vurdering.Sluttordensvurdering` | `elevforhold` |
| `utdanning.vurdering.Underveisfagvurdering` | `elevforhold` |
| `utdanning.vurdering.Underveisordensvurdering` | `elevforhold` |
| `utdanning.timeplan.Undervisningsgruppe` | `elevforhold` |
| `utdanning.timeplan.Undervisningsgruppemedlemskap` | `elevforhold` |
| `utdanning.vurdering.Vurdering` | `elevforhold` |

### ⚡ Trigger: `Kontaktlarergruppe`
When `Kontaktlarergruppe` is updated, it updates the following targets:

| Target Resource | Relations to Update (on Target) |
| :--- | :--- |
| `utdanning.elev.Basisgruppe` | `kontaktlarergruppe` |
| `utdanning.elev.Elevforhold` | `kontaktlarergruppe` |
| `utdanning.elev.Kontaktlarergruppemedlemskap` | `kontaktlarergruppe` |
| `utdanning.elev.Undervisningsforhold` | `kontaktlarergruppe` |

### ⚡ Trigger: `Person`
When `Person` is updated, it updates the following targets:

| Target Resource | Relations to Update (on Target) |
| :--- | :--- |
| `utdanning.larling.Larling` | `person` |

### ⚡ Trigger: `Persongruppe`
When `Persongruppe` is updated, it updates the following targets:

| Target Resource | Relations to Update (on Target) |
| :--- | :--- |
| `utdanning.elev.Persongruppemedlemskap` | `persongruppe` |

### ⚡ Trigger: `Skoleressurs`
When `Skoleressurs` is updated, it updates the following targets:

| Target Resource | Relations to Update (on Target) |
| :--- | :--- |
| `utdanning.vurdering.Sensor` | `skoleressurs` |
| `utdanning.utdanningsprogram.Skole` | `skoleressurs` |
| `utdanning.elev.Undervisningsforhold` | `skoleressurs` |

### ⚡ Trigger: `Undervisningsforhold`
When `Undervisningsforhold` is updated, it updates the following targets:

| Target Resource | Relations to Update (on Target) |
| :--- | :--- |
| `utdanning.elev.Basisgruppe` | `undervisningsforhold` |
| `utdanning.vurdering.Eksamensgruppe` | `undervisningsforhold` |
| `utdanning.elev.Kontaktlarergruppe` | `undervisningsforhold` |
| `utdanning.timeplan.Time` | `undervisningsforhold` |
| `utdanning.timeplan.Undervisningsgruppe` | `undervisningsforhold` |

---
## 📦 Component: utdanning.kodeverk
### ⚡ Trigger: `Karakterskala`
When `Karakterskala` is updated, it updates the following targets:

| Target Resource | Relations to Update (on Target) |
| :--- | :--- |
| `utdanning.vurdering.Karakterverdi` | `skala` |

---
## 📦 Component: utdanning.larling
### ⚡ Trigger: `Larling`
When `Larling` is updated, it updates the following targets:

| Target Resource | Relations to Update (on Target) |
| :--- | :--- |
| `utdanning.larling.AvlagtProve` | `larling` |

### ⚡ Trigger: `Person`
When `Person` is updated, it updates the following targets:

| Target Resource | Relations to Update (on Target) |
| :--- | :--- |
| `utdanning.larling.Larling` | `person` |

### ⚡ Trigger: `Virksomhet`
When `Virksomhet` is updated, it updates the following targets:

| Target Resource | Relations to Update (on Target) |
| :--- | :--- |
| `utdanning.larling.Larling` | `bedrift` |

---
## 📦 Component: utdanning.ot
### ⚡ Trigger: `Person`
When `Person` is updated, it updates the following targets:

| Target Resource | Relations to Update (on Target) |
| :--- | :--- |
| `utdanning.larling.Larling` | `person` |

---
## 📦 Component: utdanning.timeplan
### ⚡ Trigger: `Eksamen`
When `Eksamen` is updated, it updates the following targets:

| Target Resource | Relations to Update (on Target) |
| :--- | :--- |
| `utdanning.vurdering.Eksamensgruppe` | `eksamen` |
| `utdanning.timeplan.Rom` | `eksamen` |

### ⚡ Trigger: `Fag`
When `Fag` is updated, it updates the following targets:

| Target Resource | Relations to Update (on Target) |
| :--- | :--- |
| `utdanning.vurdering.Eksamensgruppe` | `fag` |
| `utdanning.elev.Elevtilrettelegging` | `fag` |
| `utdanning.timeplan.Faggruppe` | `fag` |
| `utdanning.utdanningsprogram.Programomrade` | `fag` |
| `utdanning.utdanningsprogram.Skole` | `fag` |
| `utdanning.timeplan.Undervisningsgruppe` | `fag` |

### ⚡ Trigger: `Faggruppe`
When `Faggruppe` is updated, it updates the following targets:

| Target Resource | Relations to Update (on Target) |
| :--- | :--- |
| `utdanning.timeplan.Faggruppemedlemskap` | `faggruppe` |

### ⚡ Trigger: `Faggruppemedlemskap`
When `Faggruppemedlemskap` is updated, it updates the following targets:

| Target Resource | Relations to Update (on Target) |
| :--- | :--- |
| `utdanning.elev.Varsel` | `faggruppemedlemskap` |

### ⚡ Trigger: `Rom`
When `Rom` is updated, it updates the following targets:

| Target Resource | Relations to Update (on Target) |
| :--- | :--- |
| `utdanning.timeplan.Eksamen` | `rom` |
| `utdanning.timeplan.Time` | `rom` |

### ⚡ Trigger: `Time`
When `Time` is updated, it updates the following targets:

| Target Resource | Relations to Update (on Target) |
| :--- | :--- |
| `utdanning.timeplan.Rom` | `time` |
| `utdanning.elev.Undervisningsforhold` | `time` |
| `utdanning.timeplan.Undervisningsgruppe` | `time` |

### ⚡ Trigger: `Undervisningsgruppe`
When `Undervisningsgruppe` is updated, it updates the following targets:

| Target Resource | Relations to Update (on Target) |
| :--- | :--- |
| `utdanning.elev.Elevforhold` | `undervisningsgruppe` |
| `utdanning.timeplan.Fag` | `undervisningsgruppe` |
| `utdanning.timeplan.Time` | `undervisningsgruppe` |
| `utdanning.elev.Undervisningsforhold` | `undervisningsgruppe` |
| `utdanning.timeplan.Undervisningsgruppemedlemskap` | `undervisningsgruppe` |

---
## 📦 Component: utdanning.utdanningsprogram
### ⚡ Trigger: `Arstrinn`
When `Arstrinn` is updated, it updates the following targets:

| Target Resource | Relations to Update (on Target) |
| :--- | :--- |
| `utdanning.elev.Basisgruppe` | `trinn` |
| `utdanning.utdanningsprogram.Programomrade` | `trinn` |

### ⚡ Trigger: `Programomrade`
When `Programomrade` is updated, it updates the following targets:

| Target Resource | Relations to Update (on Target) |
| :--- | :--- |
| `utdanning.utdanningsprogram.Arstrinn` | `programomrade` |
| `utdanning.elev.Elevforhold` | `programomrade` |
| `utdanning.timeplan.Fag` | `programomrade` |
| `utdanning.utdanningsprogram.Programomrademedlemskap` | `programomrade` |
| `utdanning.utdanningsprogram.Utdanningsprogram` | `programomrade` |

### ⚡ Trigger: `Skole`
When `Skole` is updated, it updates the following targets:

| Target Resource | Relations to Update (on Target) |
| :--- | :--- |
| `utdanning.elev.Basisgruppe` | `skole` |
| `utdanning.vurdering.Eksamensgruppe` | `skole` |
| `utdanning.elev.Elevforhold` | `skole` |
| `utdanning.timeplan.Fag` | `skole` |
| `utdanning.timeplan.Faggruppe` | `skole` |
| `utdanning.elev.Kontaktlarergruppe` | `skole` |
| `utdanning.elev.Skoleressurs` | `skole` |
| `utdanning.elev.Undervisningsforhold` | `skole` |
| `utdanning.timeplan.Undervisningsgruppe` | `skole` |
| `utdanning.utdanningsprogram.Utdanningsprogram` | `skole` |

### ⚡ Trigger: `Utdanningsprogram`
When `Utdanningsprogram` is updated, it updates the following targets:

| Target Resource | Relations to Update (on Target) |
| :--- | :--- |
| `utdanning.utdanningsprogram.Programomrade` | `utdanningsprogram` |
| `utdanning.utdanningsprogram.Skole` | `utdanningsprogram` |

---
## 📦 Component: utdanning.vurdering
### ⚡ Trigger: `Eksamensgruppe`
When `Eksamensgruppe` is updated, it updates the following targets:

| Target Resource | Relations to Update (on Target) |
| :--- | :--- |
| `utdanning.vurdering.Eksamensgruppemedlemskap` | `eksamensgruppe` |
| `utdanning.elev.Elevforhold` | `eksamensgruppe` |
| `utdanning.vurdering.Sensor` | `eksamensgruppe` |
| `utdanning.elev.Undervisningsforhold` | `eksamensgruppe` |

### ⚡ Trigger: `Elevfravar`
When `Elevfravar` is updated, it updates the following targets:

| Target Resource | Relations to Update (on Target) |
| :--- | :--- |
| `utdanning.vurdering.Fravarsregistrering` | `elevfravar` |

### ⚡ Trigger: `Elevvurdering`
When `Elevvurdering` is updated, it updates the following targets:

| Target Resource | Relations to Update (on Target) |
| :--- | :--- |
| `utdanning.vurdering.Eksamensvurdering` | `elevvurdering` |
| `utdanning.vurdering.Halvarsfagvurdering` | `elevvurdering` |
| `utdanning.vurdering.Halvarsordensvurdering` | `elevvurdering` |
| `utdanning.vurdering.Sluttfagvurdering` | `elevvurdering` |
| `utdanning.vurdering.Sluttordensvurdering` | `elevvurdering` |
| `utdanning.vurdering.Underveisfagvurdering` | `elevvurdering` |
| `utdanning.vurdering.Underveisordensvurdering` | `elevvurdering` |

---
*Generated: 2026-01-13*
