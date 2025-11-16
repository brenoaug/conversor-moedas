# Conversor de Moedas

## Descrição

Este é um projeto de um conversor de moedas desenvolvido em Java. A aplicação funciona via linha de comando, permitindo que o usuário escolha moedas de origem e destino e um valor para conversão. A taxa de câmbio é obtida através de uma API externa, acessada pela classe `ApiClient`.

## Configuração da Chave de API

Para que a aplicação funcione, é necessário configurar uma chave de API como uma variável de ambiente no IntelliJ IDEA.

1.  Acesse `https://www.exchangerate-api.com/` e crie uma conta para obter a sua chave de API.
2.  No menu superior do IntelliJ, vá em `Run` -> `Edit Configurations...`.
2.  Selecione a configuração de execução da sua classe `Main`. Se não houver uma, crie uma nova clicando no `+` e selecionando `Application`.
3.  No campo `Main class`, selecione a classe `Main`.
4.  No campo `Environment variables`, clique no ícone para adicionar uma nova variável.
5.  Adicione uma variável com o nome `API_KEY` e cole a sua chave no campo `Value`.
    -   Exemplo: `API_KEY=sua_chave_secreta_aqui`
6.  Clique em `OK` para salvar a variável e depois em `Apply` e `OK` para fechar a janela de configurações.

## Como Executar

1.  Abra o projeto no IntelliJ IDEA.
2.  Configure a variável de ambiente `API_KEY` conforme as instruções acima.
3.  Abra o arquivo `src/Main.java`.
4.  Clique na seta verde ao lado da declaração da classe `Main` e selecione `Run 'Main.main()'`.

A aplicação será iniciada no console do IntelliJ, onde você poderá interagir com o menu.
