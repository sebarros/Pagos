# bitss-pagos

Microservicio `pagos` de BITSS. Puerto `8085`, base de datos `pagos_db`.

## Despliegue

El workflow `.github/workflows/ci-cd.yml` ejecuta pruebas, publica la imagen en Amazon ECR y despliega únicamente este servicio en su instancia EC2 mediante AWS Systems Manager.

Configura estos secrets en el repositorio:

| Secret | Uso |
|---|---|
| `AWS_ACCESS_KEY_ID` | Credencial IAM para GitHub Actions |
| `AWS_SECRET_ACCESS_KEY` | Credencial IAM para GitHub Actions |
| `AWS_SESSION_TOKEN` | Solo para credenciales temporales |
| `AWS_REGION` | Región AWS, por ejemplo `us-east-1` |
| `INSTANCE_ID` | ID de la instancia EC2 asignada al servicio |

La instancia debe tener un rol IAM con `AmazonSSMManagedInstanceCore` y permisos de lectura ECR (`AmazonEC2ContainerRegistryReadOnly`). El repositorio ECR `bitss-pagos` debe existir.

Antes del primer despliegue, prepara la instancia con `bitss-infra/ec2/setup-app.sh` y crea `/opt/bitss/pagos.env` a partir de `bitss-infra/config/pagos.env.example`, completando IP privada de la base de datos, credenciales y URLs internas.

## Ejecución local

Configura las variables `SPRING_DATASOURCE_URL`, `SPRING_DATASOURCE_USERNAME`, `SPRING_DATASOURCE_PASSWORD` y `BITSS_JWT_SECRET` para ejecutar la aplicación fuera de AWS.
