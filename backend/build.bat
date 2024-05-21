docker compose -f .\docker-compose-build.yml up -d
./mvnw clean package -Pprod
docker compose down
docker compose -f .\docker-compose.yml build
docker compose -f .\docker-compose-arm64.yml build