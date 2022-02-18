# Projeto **ap-starter-model**

Projeto foi criado com finalidade de facilitar e padronizar projetos micro serviço

* utiliza common-lib para tratamento de exceptions e outras configs padronizadas
* utilizar referencias como boas praticas

**PS: Colabore, podem alterar e abrir PR**

# Preparando o projeto

1. Substituir marcação de variavel em Makefile

```
PROJECT_NAME=(Nome do projeto entra aqui)
```

## Deploy no K8s

1. Criar um projeto no rancher
2. Criar uma entrada para ConfigMap e outra para Secrets para ambiente dev e hml

>https://rancherv3.dasaexp.io/
```
 No ConfigMaps criar: [projeto]-cfg
 No Secrets criar: [projeto]-secret
```


3. Renomear o diretorio util/pipeline/k8s/ap-starter para o nome do projeto
4. Substituir o valor em util/pipeline/Jenkinsfile

```
PROJECT_GROUP = "***"
PROJECT_NAME = "*****"
SLACK_CHANNEL = "******"
```

5. Adicionar no config map os valores abaixo

```
data:
    ELASTIC_APM_ENABLED: "true"
    ELASTIC_APM_ENVIRONMENT: dev
    ELASTIC_APM_GLOBAL_LABELS: journey=apoio
    ELASTIC_APM_SERVER_URLS: https://apm-hydrav2.dasaexp.io:443
    ELASTIC_APM_SERVICE_NAME: (nome do projeto entra aqui)
    ELASTIC_APM_APPLICATION_PACKAGES: br.com.dasa
```

6. Adicionar no secrets map os valores abaixo

```
data:
    ELASTIC_APM_SECRET_TOKEN: (Senha entra aqui)
```