# FinacasPro

API do Porjeto Finaças Pro

## Requisitos

- [ ] CRUD de categorias
- [ ] CRUD de Movimentações
- [ ] CRUD de Usuários
- [ ] Autenticação
- [ ] Dashboard

## Documentaação

### Endpoints

- [Listar Categoria](#listar-categorias)
- [Cadastrar Categoria](#cadastrar-categoria)
- [Detalhes de Categoria](#detalhes-da-categaria)
- [Apagar Categoria](#apagar-categoria)
- [Atualizar Categorias](#apagar-categoria)

### Listar Categorias 

`GET` /categoria

Retorna um array com todas as categorias cadastradas.

#### Exemplo de Resposta
```js
[
    {
        "id": 1,
        "nome": "Alimentação",
        "icone": "fast-food"
    },
    {
        "id": 2,
        "nome": "Educação",
        "icone": "book"
    }
]
```

#### Código de Status

| código | descrição
|--------|----------
| `200`  | Sucesso
| `401`  | Usuário não autenticado. Realize autenticação em /login

---

### Cadastrar Categoria

`POST` /categoria

Cadastrar uma nova categoria para o usuário logado com os dados enviados no corpo da requisição.

#### Corpo da Requisição

| campos | tipo | obrigatório | descrição
|--------|----- | :--------: | ------------------
| `nome`| string | ✔      | Nome curto para a categoria
| `Icone`| string | ❌    | Nome do ícone conforme Maaterial Icons

```js

{
    "nome": "Alimentação",
    "icone": "fast-food"
}

```

#### Exemplo de Resposta
```js

{
    "id": 1,
    "nome": "Alimentação",
    "icone": "fast-food"
}

```

#### Código de Status

| código | descrição
|--------|----------
| `201`  | Categoria cadastrada com sucesso
| `400`  | Validação falhou. Verifique as regras para o corpo da requisição
| `401`  | Usuário não autenticado. Realize autenticação em /login

---

### Detalhes da Categaria

`GET` /categoria/`{id}`

Retorna os dados detalhados da categoria com o `id` informado no parâmentro de path.

#### Exemplo de Resposta
```js

{
    "id": 1,
    "nome": "Alimentação",
    "icone": "fast-food"
}
```

#### Código de Status

| código | descrição
|--------|----------
| `200`  | Dados da categoria retornados com sucesso
| `401`  | Usuário não autenticado. Realize autenticação em /login
| `404`  | Não existe a categoria com o `id` informado. Consulte lista em /categoria

### Apagar Categoria

`DELETE` /categoria/`{id}`

Apaga a categoria indicada pelo `id` enviado no parâmetro de path.

#### Código de Status

| código | descrição
|--------|----------
| `204`  | Categoria apagada con suceso
| `401`  | Usuário não autenticado. Realize autenticação em /login
| `404`  | Não existe a categoria com o `id` informado. Consulte lista em /categoria

---

### Atualizar Categoria

`PATCH` /categoria/`{id}`

Atualizar os dados daa categoria com o `id` informado no path, utilizando os novos dados enviados no corpo da requisição.

#### Corpo da Requisição

| campos | tipo | obrigatório | descrição
|--------|----- | :--------: | ------------------
| `nome`| string | ✔      | Nome curto para a categoria
| `Icone`| string | ❌    | Nome do ícone conforme Maaterial Icons

```js

{
    "nome": "Alimentação",
    "icone": "fast-food"
}

```

#### Exemplo de Resposta

```js

{
    "id": 1,
    "nome": "Alimentação",
    "icone": "fast-food"
}

```

#### Código de Status

| código | descrição
|--------|----------
| `200`  | Categoria apagada con suceso
| `401`  | Usuário não autenticado. Realize autenticação em /login
| `400`  | Validação falhou. Verifique as regras para o corpo da requisição
| `404`  | Não existe a categoria com o `id` informado. Consulte lista em /categoria