# 🔗 Supported Auto-Relations
This list is automatically generated from the FINT Metamodel.
It is grouped by **FINT Component** (`domain.package`).

## 📦 Component: administrasjon.fullmakt
### ⚡ Trigger: `fullmakt`
When `fullmakt` is updated, it updates the following targets:

| Target Resource | Relations to Update (on Target) |
| :--- | :--- |
| `administrasjon.kodeverk.Kontodimensjon` | `fullmakt` |

### ⚡ Trigger: `rolle`
When `rolle` is updated, it updates the following targets:

| Target Resource | Relations to Update (on Target) |
| :--- | :--- |
| `administrasjon.fullmakt.Fullmakt` | `rolle` |

---
## 📦 Component: administrasjon.kodeverk
### ⚡ Trigger: `ansvar`
When `ansvar` is updated, it updates the following targets:

| Target Resource | Relations to Update (on Target) |
| :--- | :--- |
| `administrasjon.kodeverk.Ansvar` | `overordnet` |
| `administrasjon.organisasjon.Organisasjonselement` | `ansvar` |

### ⚡ Trigger: `funksjon`
When `funksjon` is updated, it updates the following targets:

| Target Resource | Relations to Update (on Target) |
| :--- | :--- |
| `administrasjon.kodeverk.Funksjon` | `overordnet` |

### ⚡ Trigger: `prosjekt`
When `prosjekt` is updated, it updates the following targets:

| Target Resource | Relations to Update (on Target) |
| :--- | :--- |
| `administrasjon.kodeverk.Prosjektart` | `prosjekt` |

### ⚡ Trigger: `prosjektart`
When `prosjektart` is updated, it updates the following targets:

| Target Resource | Relations to Update (on Target) |
| :--- | :--- |
| `administrasjon.kodeverk.Prosjektart` | `overordnet` |

---
## 📦 Component: administrasjon.organisasjon
### ⚡ Trigger: `arbeidslokasjon`
When `arbeidslokasjon` is updated, it updates the following targets:

| Target Resource | Relations to Update (on Target) |
| :--- | :--- |
| `administrasjon.personal.Arbeidsforhold` | `arbeidslokasjon` |

### ⚡ Trigger: `organisasjonselement`
When `organisasjonselement` is updated, it updates the following targets:

| Target Resource | Relations to Update (on Target) |
| :--- | :--- |
| `administrasjon.kodeverk.Ansvar` | `organisasjonselement` |
| `administrasjon.personal.Arbeidsforhold` | `arbeidssted` |

---
## 📦 Component: administrasjon.personal
### ⚡ Trigger: `arbeidsforhold`
When `arbeidsforhold` is updated, it updates the following targets:

| Target Resource | Relations to Update (on Target) |
| :--- | :--- |
| `administrasjon.personal.Fastlonn` | `arbeidsforhold` |
| `administrasjon.personal.Fasttillegg` | `arbeidsforhold` |
| `administrasjon.personal.Fravar` | `arbeidsforhold` |
| `administrasjon.personal.Variabellonn` | `arbeidsforhold` |

### ⚡ Trigger: `fravar`
When `fravar` is updated, it updates the following targets:

| Target Resource | Relations to Update (on Target) |
| :--- | :--- |
| `administrasjon.personal.Arbeidsforhold` | `fravar` |

### ⚡ Trigger: `personalressurs`
When `personalressurs` is updated, it updates the following targets:

| Target Resource | Relations to Update (on Target) |
| :--- | :--- |
| `administrasjon.personal.Arbeidsforhold` | `personalleder`, `personalressurs` |
| `administrasjon.fullmakt.Fullmakt` | `fullmektig`, `stedfortreder` |
| `administrasjon.organisasjon.Organisasjonselement` | `leder` |

---
## 📦 Component: arkiv.noark
### ⚡ Trigger: `arkivdel`
When `arkivdel` is updated, it updates the following targets:

| Target Resource | Relations to Update (on Target) |
| :--- | :--- |
| `arkiv.noark.Klassifikasjonssystem` | `arkivdel` |
| `arkiv.noark.Mappe` | `arkivdel` |
| `arkiv.noark.Registrering` | `arkivdel` |

### ⚡ Trigger: `arkivressurs`
When `arkivressurs` is updated, it updates the following targets:

| Target Resource | Relations to Update (on Target) |
| :--- | :--- |
| `arkiv.noark.Autorisasjon` | `arkivressurs` |
| `arkiv.noark.Tilgang` | `arkivressurs` |

### ⚡ Trigger: `autorisasjon`
When `autorisasjon` is updated, it updates the following targets:

| Target Resource | Relations to Update (on Target) |
| :--- | :--- |
| `arkiv.noark.Arkivressurs` | `autorisasjon` |

### ⚡ Trigger: `klassifikasjonssystem`
When `klassifikasjonssystem` is updated, it updates the following targets:

| Target Resource | Relations to Update (on Target) |
| :--- | :--- |
| `arkiv.noark.Arkivdel` | `klassifikasjonssystem` |

### ⚡ Trigger: `tilgang`
When `tilgang` is updated, it updates the following targets:

| Target Resource | Relations to Update (on Target) |
| :--- | :--- |
| `arkiv.noark.Arkivressurs` | `tilgang` |

---
## 📦 Component: felles.kodeverk
### ⚡ Trigger: `fylke`
When `fylke` is updated, it updates the following targets:

| Target Resource | Relations to Update (on Target) |
| :--- | :--- |
| `felles.kodeverk.Kommune` | `fylke` |

---
## 📦 Component: okonomi.faktura
### ⚡ Trigger: `fakturagrunnlag`
When `fakturagrunnlag` is updated, it updates the following targets:

| Target Resource | Relations to Update (on Target) |
| :--- | :--- |
| `okonomi.faktura.Faktura` | `fakturagrunnlag` |

### ⚡ Trigger: `fakturautsteder`
When `fakturautsteder` is updated, it updates the following targets:

| Target Resource | Relations to Update (on Target) |
| :--- | :--- |
| `okonomi.faktura.Fakturagrunnlag` | `fakturautsteder` |
| `okonomi.kodeverk.Vare` | `fakturautsteder` |

---
## 📦 Component: okonomi.regnskap
### ⚡ Trigger: `transaksjon`
When `transaksjon` is updated, it updates the following targets:

| Target Resource | Relations to Update (on Target) |
| :--- | :--- |
| `okonomi.regnskap.Postering` | `transaksjon` |

---
## 📦 Component: personvern.samtykke
### ⚡ Trigger: `behandling`
When `behandling` is updated, it updates the following targets:

| Target Resource | Relations to Update (on Target) |
| :--- | :--- |
| `personvern.samtykke.Samtykke` | `behandling` |

### ⚡ Trigger: `tjeneste`
When `tjeneste` is updated, it updates the following targets:

| Target Resource | Relations to Update (on Target) |
| :--- | :--- |
| `personvern.samtykke.Behandling` | `tjeneste` |

---
## 📦 Component: ressurs.datautstyr
### ⚡ Trigger: `digitalenhet`
When `digitalenhet` is updated, it updates the following targets:

| Target Resource | Relations to Update (on Target) |
| :--- | :--- |
| `ressurs.datautstyr.Enhetsgruppemedlemskap` | `digitalEnhet` |

### ⚡ Trigger: `enhetsgruppe`
When `enhetsgruppe` is updated, it updates the following targets:

| Target Resource | Relations to Update (on Target) |
| :--- | :--- |
| `ressurs.datautstyr.Enhetsgruppemedlemskap` | `enhetsgruppe` |

---
## 📦 Component: ressurs.eiendel
### ⚡ Trigger: `applikasjon`
When `applikasjon` is updated, it updates the following targets:

| Target Resource | Relations to Update (on Target) |
| :--- | :--- |
| `ressurs.eiendel.Applikasjonsressurs` | `applikasjon` |

### ⚡ Trigger: `applikasjonsressurs`
When `applikasjonsressurs` is updated, it updates the following targets:

| Target Resource | Relations to Update (on Target) |
| :--- | :--- |
| `ressurs.eiendel.Applikasjonsressurstilgjengelighet` | `ressurs` |

---
## 📦 Component: ressurs.tilgang
### ⚡ Trigger: `identitet`
When `identitet` is updated, it updates the following targets:

| Target Resource | Relations to Update (on Target) |
| :--- | :--- |
| `ressurs.tilgang.Rettighet` | `identitet` |

### ⚡ Trigger: `rettighet`
When `rettighet` is updated, it updates the following targets:

| Target Resource | Relations to Update (on Target) |
| :--- | :--- |
| `ressurs.tilgang.Identitet` | `rettighet` |

---
## 📦 Component: utdanning.elev
### ⚡ Trigger: `basisgruppe`
When `basisgruppe` is updated, it updates the following targets:

| Target Resource | Relations to Update (on Target) |
| :--- | :--- |
| `utdanning.elev.Basisgruppemedlemskap` | `basisgruppe` |
| `utdanning.elev.Elevforhold` | `basisgruppe` |
| `utdanning.elev.Kontaktlarergruppe` | `basisgruppe` |
| `utdanning.elev.Undervisningsforhold` | `basisgruppe` |

### ⚡ Trigger: `elev`
When `elev` is updated, it updates the following targets:

| Target Resource | Relations to Update (on Target) |
| :--- | :--- |
| `utdanning.elev.Elevforhold` | `elev` |

### ⚡ Trigger: `elevforhold`
When `elevforhold` is updated, it updates the following targets:

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

### ⚡ Trigger: `kontaktlarergruppe`
When `kontaktlarergruppe` is updated, it updates the following targets:

| Target Resource | Relations to Update (on Target) |
| :--- | :--- |
| `utdanning.elev.Basisgruppe` | `kontaktlarergruppe` |
| `utdanning.elev.Elevforhold` | `kontaktlarergruppe` |
| `utdanning.elev.Kontaktlarergruppemedlemskap` | `kontaktlarergruppe` |
| `utdanning.elev.Undervisningsforhold` | `kontaktlarergruppe` |

### ⚡ Trigger: `person`
When `person` is updated, it updates the following targets:

| Target Resource | Relations to Update (on Target) |
| :--- | :--- |
| `utdanning.larling.Larling` | `person` |

### ⚡ Trigger: `persongruppe`
When `persongruppe` is updated, it updates the following targets:

| Target Resource | Relations to Update (on Target) |
| :--- | :--- |
| `utdanning.elev.Persongruppemedlemskap` | `persongruppe` |

### ⚡ Trigger: `skoleressurs`
When `skoleressurs` is updated, it updates the following targets:

| Target Resource | Relations to Update (on Target) |
| :--- | :--- |
| `utdanning.vurdering.Sensor` | `skoleressurs` |
| `utdanning.utdanningsprogram.Skole` | `skoleressurs` |
| `utdanning.elev.Undervisningsforhold` | `skoleressurs` |

### ⚡ Trigger: `undervisningsforhold`
When `undervisningsforhold` is updated, it updates the following targets:

| Target Resource | Relations to Update (on Target) |
| :--- | :--- |
| `utdanning.elev.Basisgruppe` | `undervisningsforhold` |
| `utdanning.vurdering.Eksamensgruppe` | `undervisningsforhold` |
| `utdanning.elev.Kontaktlarergruppe` | `undervisningsforhold` |
| `utdanning.timeplan.Time` | `undervisningsforhold` |
| `utdanning.timeplan.Undervisningsgruppe` | `undervisningsforhold` |

---
## 📦 Component: utdanning.kodeverk
### ⚡ Trigger: `karakterskala`
When `karakterskala` is updated, it updates the following targets:

| Target Resource | Relations to Update (on Target) |
| :--- | :--- |
| `utdanning.vurdering.Karakterverdi` | `skala` |

---
## 📦 Component: utdanning.larling
### ⚡ Trigger: `larling`
When `larling` is updated, it updates the following targets:

| Target Resource | Relations to Update (on Target) |
| :--- | :--- |
| `utdanning.larling.AvlagtProve` | `larling` |

### ⚡ Trigger: `person`
When `person` is updated, it updates the following targets:

| Target Resource | Relations to Update (on Target) |
| :--- | :--- |
| `utdanning.larling.Larling` | `person` |

### ⚡ Trigger: `virksomhet`
When `virksomhet` is updated, it updates the following targets:

| Target Resource | Relations to Update (on Target) |
| :--- | :--- |
| `utdanning.larling.Larling` | `bedrift` |

---
## 📦 Component: utdanning.ot
### ⚡ Trigger: `person`
When `person` is updated, it updates the following targets:

| Target Resource | Relations to Update (on Target) |
| :--- | :--- |
| `utdanning.larling.Larling` | `person` |

---
## 📦 Component: utdanning.timeplan
### ⚡ Trigger: `eksamen`
When `eksamen` is updated, it updates the following targets:

| Target Resource | Relations to Update (on Target) |
| :--- | :--- |
| `utdanning.vurdering.Eksamensgruppe` | `eksamen` |
| `utdanning.timeplan.Rom` | `eksamen` |

### ⚡ Trigger: `fag`
When `fag` is updated, it updates the following targets:

| Target Resource | Relations to Update (on Target) |
| :--- | :--- |
| `utdanning.vurdering.Eksamensgruppe` | `fag` |
| `utdanning.elev.Elevtilrettelegging` | `fag` |
| `utdanning.timeplan.Faggruppe` | `fag` |
| `utdanning.utdanningsprogram.Programomrade` | `fag` |
| `utdanning.utdanningsprogram.Skole` | `fag` |
| `utdanning.timeplan.Undervisningsgruppe` | `fag` |

### ⚡ Trigger: `faggruppe`
When `faggruppe` is updated, it updates the following targets:

| Target Resource | Relations to Update (on Target) |
| :--- | :--- |
| `utdanning.timeplan.Faggruppemedlemskap` | `faggruppe` |

### ⚡ Trigger: `faggruppemedlemskap`
When `faggruppemedlemskap` is updated, it updates the following targets:

| Target Resource | Relations to Update (on Target) |
| :--- | :--- |
| `utdanning.elev.Varsel` | `faggruppemedlemskap` |

### ⚡ Trigger: `rom`
When `rom` is updated, it updates the following targets:

| Target Resource | Relations to Update (on Target) |
| :--- | :--- |
| `utdanning.timeplan.Eksamen` | `rom` |
| `utdanning.timeplan.Time` | `rom` |

### ⚡ Trigger: `time`
When `time` is updated, it updates the following targets:

| Target Resource | Relations to Update (on Target) |
| :--- | :--- |
| `utdanning.timeplan.Rom` | `time` |
| `utdanning.elev.Undervisningsforhold` | `time` |
| `utdanning.timeplan.Undervisningsgruppe` | `time` |

### ⚡ Trigger: `undervisningsgruppe`
When `undervisningsgruppe` is updated, it updates the following targets:

| Target Resource | Relations to Update (on Target) |
| :--- | :--- |
| `utdanning.elev.Elevforhold` | `undervisningsgruppe` |
| `utdanning.timeplan.Fag` | `undervisningsgruppe` |
| `utdanning.timeplan.Time` | `undervisningsgruppe` |
| `utdanning.elev.Undervisningsforhold` | `undervisningsgruppe` |
| `utdanning.timeplan.Undervisningsgruppemedlemskap` | `undervisningsgruppe` |

---
## 📦 Component: utdanning.utdanningsprogram
### ⚡ Trigger: `arstrinn`
When `arstrinn` is updated, it updates the following targets:

| Target Resource | Relations to Update (on Target) |
| :--- | :--- |
| `utdanning.elev.Basisgruppe` | `trinn` |
| `utdanning.utdanningsprogram.Programomrade` | `trinn` |

### ⚡ Trigger: `programomrade`
When `programomrade` is updated, it updates the following targets:

| Target Resource | Relations to Update (on Target) |
| :--- | :--- |
| `utdanning.utdanningsprogram.Arstrinn` | `programomrade` |
| `utdanning.elev.Elevforhold` | `programomrade` |
| `utdanning.timeplan.Fag` | `programomrade` |
| `utdanning.utdanningsprogram.Programomrademedlemskap` | `programomrade` |
| `utdanning.utdanningsprogram.Utdanningsprogram` | `programomrade` |

### ⚡ Trigger: `skole`
When `skole` is updated, it updates the following targets:

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

### ⚡ Trigger: `utdanningsprogram`
When `utdanningsprogram` is updated, it updates the following targets:

| Target Resource | Relations to Update (on Target) |
| :--- | :--- |
| `utdanning.utdanningsprogram.Programomrade` | `utdanningsprogram` |
| `utdanning.utdanningsprogram.Skole` | `utdanningsprogram` |

---
## 📦 Component: utdanning.vurdering
### ⚡ Trigger: `eksamensgruppe`
When `eksamensgruppe` is updated, it updates the following targets:

| Target Resource | Relations to Update (on Target) |
| :--- | :--- |
| `utdanning.vurdering.Eksamensgruppemedlemskap` | `eksamensgruppe` |
| `utdanning.elev.Elevforhold` | `eksamensgruppe` |
| `utdanning.vurdering.Sensor` | `eksamensgruppe` |
| `utdanning.elev.Undervisningsforhold` | `eksamensgruppe` |

### ⚡ Trigger: `elevfravar`
When `elevfravar` is updated, it updates the following targets:

| Target Resource | Relations to Update (on Target) |
| :--- | :--- |
| `utdanning.vurdering.Fravarsregistrering` | `elevfravar` |

### ⚡ Trigger: `elevvurdering`
When `elevvurdering` is updated, it updates the following targets:

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
