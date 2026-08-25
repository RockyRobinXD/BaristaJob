# Examenopdracht Enterprise Web Development: Java

- Student: Robin Van der Borght
- Studentennummer: 202408836
- E-mailadres: <robin.vanderborght@student.hogent.be>

## .env File

Voor het project lokaal te kunnen gebruiken moet er een .env file aanwezig zijn met volgende inhoud

```bash
# Docker env variables
HOSTPORT=6000
POSTGRES_IMAGE=postgres:18.3
POSTGRES_DB=BaristaJobDB
POSTGRES_USER=systemadmin
POSTGRES_PASSWORD=OxCWgJNtwz1wiydhYri0W0OW0F7d1fLnl90gZxSKDjkxivphK4
PGDATA=/var/lib/postgresql/18/docker

DATABASE_URL=jdbc:postgresql://localhost:6000/BaristaJobDB
```

## Opstarten

- Zorg eerst voor een .env file in het project volgens het voorbeeldtemplate hierboven
- Docker conntainer opstarten met `docker-compose up -d`.
- Databank zou automatisch met gegevens opgevuld zijn sinds het dev profile actief is in de aplication.yaml.
- Start de app op en deze zou dan moeten runnen op <http://localhost:8080>.

## Inloggegevens

### Admin-login
- Email: sophie.vandamme@baristajob.be
- Password: BaristaJob

### Barista-login
- Email: emma.peeters@baristajob.be
- Password: BaristaJob