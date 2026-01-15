# 🔗 Supported Auto-Relations
This list is automatically generated from the FINT Metamodel.
It is grouped by **FINT Component** (`domain.package`).

## 📦 Component: administrasjon.fullmakt
### ⚡ Trigger: `rolle`
When `rolle` is updated, it updates the following targets:

| Target Resource | Relations to Update (on Target) |
| :--- | :--- |
| `administrasjon.fullmakt.fullmakt` | `rolle` |

---
## 📦 Component: administrasjon.kodeverk
### ⚡ Trigger: `ansvar`
When `ansvar` is updated, it updates the following targets:

| Target Resource | Relations to Update (on Target) |
| :--- | :--- |
| `administrasjon.kodeverk.ansvar` | `overordnet` |

### ⚡ Trigger: `funksjon`
When `funksjon` is updated, it updates the following targets:

| Target Resource | Relations to Update (on Target) |
| :--- | :--- |
| `administrasjon.kodeverk.funksjon` | `overordnet` |

### ⚡ Trigger: `prosjekt`
When `prosjekt` is updated, it updates the following targets:

| Target Resource | Relations to Update (on Target) |
| :--- | :--- |
| `administrasjon.kodeverk.prosjektart` | `prosjekt` |

### ⚡ Trigger: `prosjektart`
When `prosjektart` is updated, it updates the following targets:

| Target Resource | Relations to Update (on Target) |
| :--- | :--- |
| `administrasjon.kodeverk.prosjektart` | `overordnet` |

---
## 📦 Component: administrasjon.organisasjon
### ⚡ Trigger: `arbeidslokasjon`
When `arbeidslokasjon` is updated, it updates the following targets:

| Target Resource | Relations to Update (on Target) |
| :--- | :--- |
| `administrasjon.personal.arbeidsforhold` | `arbeidslokasjon` |

### ⚡ Trigger: `organisasjonselement`
When `organisasjonselement` is updated, it updates the following targets:

| Target Resource | Relations to Update (on Target) |
| :--- | :--- |
| `administrasjon.personal.arbeidsforhold` | `arbeidssted` |

---
## 📦 Component: administrasjon.personal
### ⚡ Trigger: `arbeidsforhold`
When `arbeidsforhold` is updated, it updates the following targets:

| Target Resource | Relations to Update (on Target) |
| :--- | :--- |
| `administrasjon.personal.fastlonn` | `arbeidsforhold` |
| `administrasjon.personal.fasttillegg` | `arbeidsforhold` |
| `administrasjon.personal.variabellonn` | `arbeidsforhold` |

### ⚡ Trigger: `personalressurs`
When `personalressurs` is updated, it updates the following targets:

| Target Resource | Relations to Update (on Target) |
| :--- | :--- |
| `administrasjon.personal.arbeidsforhold` | `personalleder`, `personalressurs` |
| `administrasjon.fullmakt.fullmakt` | `fullmektig`, `stedfortreder` |
| `administrasjon.organisasjon.organisasjonselement` | `leder` |

---
## 📦 Component: felles.kodeverk
### ⚡ Trigger: `fylke`
When `fylke` is updated, it updates the following targets:

| Target Resource | Relations to Update (on Target) |
| :--- | :--- |
| `felles.kodeverk.kommune` | `fylke` |

---
## 📦 Component: okonomi.faktura
### ⚡ Trigger: `fakturagrunnlag`
When `fakturagrunnlag` is updated, it updates the following targets:

| Target Resource | Relations to Update (on Target) |
| :--- | :--- |
| `okonomi.faktura.faktura` | `fakturagrunnlag` |

### ⚡ Trigger: `fakturautsteder`
When `fakturautsteder` is updated, it updates the following targets:

| Target Resource | Relations to Update (on Target) |
| :--- | :--- |
| `okonomi.faktura.fakturagrunnlag` | `fakturautsteder` |
| `okonomi.kodeverk.vare` | `fakturautsteder` |

---
## 📦 Component: okonomi.regnskap
### ⚡ Trigger: `transaksjon`
When `transaksjon` is updated, it updates the following targets:

| Target Resource | Relations to Update (on Target) |
| :--- | :--- |
| `okonomi.regnskap.postering` | `transaksjon` |

---
## 📦 Component: personvern.samtykke
### ⚡ Trigger: `behandling`
When `behandling` is updated, it updates the following targets:

| Target Resource | Relations to Update (on Target) |
| :--- | :--- |
| `personvern.samtykke.samtykke` | `behandling` |

### ⚡ Trigger: `tjeneste`
When `tjeneste` is updated, it updates the following targets:

| Target Resource | Relations to Update (on Target) |
| :--- | :--- |
| `personvern.samtykke.behandling` | `tjeneste` |

---
## 📦 Component: ressurs.datautstyr
### ⚡ Trigger: `digitalenhet`
When `digitalenhet` is updated, it updates the following targets:

| Target Resource | Relations to Update (on Target) |
| :--- | :--- |
| `ressurs.datautstyr.enhetsgruppemedlemskap` | `digitalEnhet` |

### ⚡ Trigger: `enhetsgruppe`
When `enhetsgruppe` is updated, it updates the following targets:

| Target Resource | Relations to Update (on Target) |
| :--- | :--- |
| `ressurs.datautstyr.enhetsgruppemedlemskap` | `enhetsgruppe` |

---
## 📦 Component: ressurs.eiendel
### ⚡ Trigger: `applikasjon`
When `applikasjon` is updated, it updates the following targets:

| Target Resource | Relations to Update (on Target) |
| :--- | :--- |
| `ressurs.eiendel.applikasjonsressurs` | `applikasjon` |

### ⚡ Trigger: `applikasjonsressurs`
When `applikasjonsressurs` is updated, it updates the following targets:

| Target Resource | Relations to Update (on Target) |
| :--- | :--- |
| `ressurs.eiendel.applikasjonsressurstilgjengelighet` | `ressurs` |

---
## 📦 Component: utdanning.elev
### ⚡ Trigger: `basisgruppe`
When `basisgruppe` is updated, it updates the following targets:

| Target Resource | Relations to Update (on Target) |
| :--- | :--- |
| `utdanning.elev.basisgruppemedlemskap` | `basisgruppe` |

### ⚡ Trigger: `elev`
When `elev` is updated, it updates the following targets:

| Target Resource | Relations to Update (on Target) |
| :--- | :--- |
| `utdanning.elev.elevforhold` | `elev` |

### ⚡ Trigger: `elevforhold`
When `elevforhold` is updated, it updates the following targets:

| Target Resource | Relations to Update (on Target) |
| :--- | :--- |
| `utdanning.elev.basisgruppemedlemskap` | `elevforhold` |
| `utdanning.vurdering.eksamensgruppemedlemskap` | `elevforhold` |
| `utdanning.elev.elevtilrettelegging` | `elev` |
| `utdanning.vurdering.fravar` | `elevforhold` |
| `utdanning.vurdering.fravarsoversikt` | `elevforhold` |
| `utdanning.vurdering.halvarsfagvurdering` | `elevforhold` |
| `utdanning.vurdering.halvarsordensvurdering` | `elevforhold` |
| `utdanning.elev.kontaktlarergruppemedlemskap` | `elevforhold` |
| `utdanning.elev.persongruppemedlemskap` | `elevforhold` |
| `utdanning.utdanningsprogram.programomrademedlemskap` | `elevforhold` |
| `utdanning.vurdering.sluttfagvurdering` | `elevforhold` |
| `utdanning.vurdering.sluttordensvurdering` | `elevforhold` |
| `utdanning.vurdering.underveisfagvurdering` | `elevforhold` |
| `utdanning.vurdering.underveisordensvurdering` | `elevforhold` |
| `utdanning.timeplan.undervisningsgruppemedlemskap` | `elevforhold` |
| `utdanning.vurdering.vurdering` | `elevforhold` |

### ⚡ Trigger: `kontaktlarergruppe`
When `kontaktlarergruppe` is updated, it updates the following targets:

| Target Resource | Relations to Update (on Target) |
| :--- | :--- |
| `utdanning.elev.kontaktlarergruppemedlemskap` | `kontaktlarergruppe` |

### ⚡ Trigger: `person`
When `person` is updated, it updates the following targets:

| Target Resource | Relations to Update (on Target) |
| :--- | :--- |
| `utdanning.larling.larling` | `person` |

### ⚡ Trigger: `persongruppe`
When `persongruppe` is updated, it updates the following targets:

| Target Resource | Relations to Update (on Target) |
| :--- | :--- |
| `utdanning.elev.persongruppemedlemskap` | `persongruppe` |

### ⚡ Trigger: `skoleressurs`
When `skoleressurs` is updated, it updates the following targets:

| Target Resource | Relations to Update (on Target) |
| :--- | :--- |
| `utdanning.vurdering.sensor` | `skoleressurs` |
| `utdanning.elev.undervisningsforhold` | `skoleressurs` |

---
## 📦 Component: utdanning.kodeverk
### ⚡ Trigger: `karakterskala`
When `karakterskala` is updated, it updates the following targets:

| Target Resource | Relations to Update (on Target) |
| :--- | :--- |
| `utdanning.vurdering.karakterverdi` | `skala` |

---
## 📦 Component: utdanning.larling
### ⚡ Trigger: `larling`
When `larling` is updated, it updates the following targets:

| Target Resource | Relations to Update (on Target) |
| :--- | :--- |
| `utdanning.larling.avlagtprove` | `larling` |

### ⚡ Trigger: `person`
When `person` is updated, it updates the following targets:

| Target Resource | Relations to Update (on Target) |
| :--- | :--- |
| `utdanning.larling.larling` | `person` |

### ⚡ Trigger: `virksomhet`
When `virksomhet` is updated, it updates the following targets:

| Target Resource | Relations to Update (on Target) |
| :--- | :--- |
| `utdanning.larling.larling` | `bedrift` |

---
## 📦 Component: utdanning.ot
### ⚡ Trigger: `person`
When `person` is updated, it updates the following targets:

| Target Resource | Relations to Update (on Target) |
| :--- | :--- |
| `utdanning.larling.larling` | `person` |

---
## 📦 Component: utdanning.timeplan
### ⚡ Trigger: `eksamen`
When `eksamen` is updated, it updates the following targets:

| Target Resource | Relations to Update (on Target) |
| :--- | :--- |
| `utdanning.vurdering.eksamensgruppe` | `eksamen` |

### ⚡ Trigger: `fag`
When `fag` is updated, it updates the following targets:

| Target Resource | Relations to Update (on Target) |
| :--- | :--- |
| `utdanning.vurdering.eksamensgruppe` | `fag` |
| `utdanning.elev.elevtilrettelegging` | `fag` |
| `utdanning.timeplan.faggruppe` | `fag` |

### ⚡ Trigger: `faggruppe`
When `faggruppe` is updated, it updates the following targets:

| Target Resource | Relations to Update (on Target) |
| :--- | :--- |
| `utdanning.timeplan.faggruppemedlemskap` | `faggruppe` |

### ⚡ Trigger: `faggruppemedlemskap`
When `faggruppemedlemskap` is updated, it updates the following targets:

| Target Resource | Relations to Update (on Target) |
| :--- | :--- |
| `utdanning.elev.varsel` | `faggruppemedlemskap` |

### ⚡ Trigger: `undervisningsgruppe`
When `undervisningsgruppe` is updated, it updates the following targets:

| Target Resource | Relations to Update (on Target) |
| :--- | :--- |
| `utdanning.timeplan.undervisningsgruppemedlemskap` | `undervisningsgruppe` |

---
## 📦 Component: utdanning.utdanningsprogram
### ⚡ Trigger: `arstrinn`
When `arstrinn` is updated, it updates the following targets:

| Target Resource | Relations to Update (on Target) |
| :--- | :--- |
| `utdanning.elev.basisgruppe` | `trinn` |

### ⚡ Trigger: `programomrade`
When `programomrade` is updated, it updates the following targets:

| Target Resource | Relations to Update (on Target) |
| :--- | :--- |
| `utdanning.elev.elevforhold` | `programomrade` |
| `utdanning.utdanningsprogram.programomrademedlemskap` | `programomrade` |

### ⚡ Trigger: `skole`
When `skole` is updated, it updates the following targets:

| Target Resource | Relations to Update (on Target) |
| :--- | :--- |
| `utdanning.elev.basisgruppe` | `skole` |
| `utdanning.vurdering.eksamensgruppe` | `skole` |
| `utdanning.elev.elevforhold` | `skole` |
| `utdanning.timeplan.faggruppe` | `skole` |
| `utdanning.elev.kontaktlarergruppe` | `skole` |
| `utdanning.elev.undervisningsforhold` | `skole` |
| `utdanning.timeplan.undervisningsgruppe` | `skole` |

---
## 📦 Component: utdanning.vurdering
### ⚡ Trigger: `eksamensgruppe`
When `eksamensgruppe` is updated, it updates the following targets:

| Target Resource | Relations to Update (on Target) |
| :--- | :--- |
| `utdanning.vurdering.eksamensgruppemedlemskap` | `eksamensgruppe` |
| `utdanning.vurdering.sensor` | `eksamensgruppe` |

### ⚡ Trigger: `elevfravar`
When `elevfravar` is updated, it updates the following targets:

| Target Resource | Relations to Update (on Target) |
| :--- | :--- |
| `utdanning.vurdering.fravarsregistrering` | `elevfravar` |

### ⚡ Trigger: `elevvurdering`
When `elevvurdering` is updated, it updates the following targets:

| Target Resource | Relations to Update (on Target) |
| :--- | :--- |
| `utdanning.vurdering.eksamensvurdering` | `elevvurdering` |
| `utdanning.vurdering.halvarsfagvurdering` | `elevvurdering` |
| `utdanning.vurdering.halvarsordensvurdering` | `elevvurdering` |
| `utdanning.vurdering.sluttfagvurdering` | `elevvurdering` |
| `utdanning.vurdering.sluttordensvurdering` | `elevvurdering` |
| `utdanning.vurdering.underveisfagvurdering` | `elevvurdering` |
| `utdanning.vurdering.underveisordensvurdering` | `elevvurdering` |

---
*Generated: 2026-01-15*
