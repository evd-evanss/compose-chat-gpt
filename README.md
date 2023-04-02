## Chat GPT - POC
Prova de conceito da LIB Open Ai - https://openai.com/blog/openai-api

### Primeiros passos
1 - Crie uma conta em https://openai.com/blog/openai-api e gere uma chave

2 - Abra o arquivo gradle.properties e inclua sua chave gerada no passo anterior.
API_KEY="YourApiKey"

3- Execute o projeto

### Por quê usar o Jetpack Compose?
Para criar a view de forma rápida, utilizei o Jetpack Compose, me livrando dos boilerplates que teria
que implementar por meio de ViewHolders e RecyclerView.

### Arquitetura utilizada - MVI 

Model 
Representa os estado da interface do usuário

View 
Representa a interface do usuário

Intent
Representa os eventos gerados pelo usuário ao interagir com a View de forma explícita ou implícita.

![image](https://user-images.githubusercontent.com/26419059/229371943-f7c5f3d7-6014-455c-9ed1-982aa43c3d5f.png)
