<p align="center">
  <img src="src/main/resources/static/images/logo2.png" alt="Logo RedeSegura" height="150">
</p>
<h1 align="center">ㅤㅤ</h1>
<h3 align="center">Vencedora do 2º Hackathon do Programa Hackers do Bem</h3>

<p align='center'> 
    <img src="https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white"/>  
    <img src="https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white"/>
    <img src="https://img.shields.io/badge/Thymeleaf-%23005C0F.svg?style=for-the-badge&logo=Thymeleaf&logoColor=white"/>
    <img src="https://img.shields.io/badge/Apache%20Maven-C71A36?style=for-the-badge&logo=Apache%20Maven&logoColor=white"/>
    <img src="https://img.shields.io/badge/docker-%230db7ed.svg?style=for-the-badge&logo=docker&logoColor=white"/>
    <img src="https://img.shields.io/badge/mysql-4479A1.svg?style=for-the-badge&logo=mysql&logoColor=white"/>
</p>    

[Testando vulnerabilidades](https://github.com/user-attachments/assets/26284f5e-b72d-40b7-b838-9676698bcf10)

# Introdução
Rede Segura é uma aplicação desenvolvida para identificar e mitigar vulnerabilidades em sistemas críticos. Esta solução foi a grande vencedora do 2º Hackathon do Programa Hackers do Bem,
realizado em Natal-RN, no Instituto Metrópole Digital da UFRN, nos dias 17 e 18 de agosto de 2024. O hackathon teve como desafio criar uma solução inovadora para melhorar a eficiência e 
eficácia na identificação e gerenciamento de vulnerabilidades nas redes dos clientes do Sistema RNP. 

<br>

<b>A aplicação Rede Segura foi desenvolvida com o objetivo de atender às necessidades da RNP (Rede Nacional de Ensino e Pesquisa), 
especificamente para identificar e gerenciar vulnerabilidades em sistemas críticos de suas instituições associadas. </b>

<br>
<b>O projeto foi desenvolvido por uma equipe composta por Matheus Vidal, Isabela Mendes, Marcos Antônio e Kauê Lustosa, e recebeu o primeiro lugar na competição.</b>

## Documentos relacionados
<b>🖥️ Protótipos de tela:</b> [clique aqui](https://www.canva.com/design/DAGOJc9xyjQ/1fwCKY3JDpHgoSCO-T1GSw/view?mode=prototype)
<br>
<b>📰 Matéria sobre o evento:</b> [clique aqui](https://www.rnp.br/noticias/2o-hackathon-do-hackers-do-bem-reune-jovens-talentos-em-natal-rn-para-inovar-na)
<br>
<b>📌 Apresentação do problema:</b> [clique aqui](https://github.com/matheusvidal21/RedeSegura/blob/main/src/main/resources/static/files/apresentacao_problema.pdf)
<br>
<b>🧑‍💻 Apresentação do Hackathon:</b> [clique aqui](https://github.com/matheusvidal21/RedeSegura/blob/main/src/main/resources/static/files/inicio_hackathon.pdf)

# 🧾 Funcionalidades
A Rede Segura oferece as seguintes funcionalidades principais:
- **Detecção de Vulnerabilidades**: Realiza testes automáticos para identificar uma vasta gama de vulnerabilidades em redes e sistemas.
  
- **Gerenciamento de Vulnerabilidades**: Permite o acompanhamento do status das vulnerabilidades identificadas, incluindo a resolução e o histórico de alterações.
  
- **Relatórios Detalhados**: Gera relatórios detalhados em formato PDF sobre as vulnerabilidades encontradas, contendo gráficos e insights importantes para a correção das falhas.
  
- **Ambientes de Teste**: Possui ambientes de teste configuráveis utilizando Docker, para simular condições reais de redes vulneráveis e seguras.

# 🦠 Tipos de Vulnerabilidades Testadas
A aplicação é capaz de testar uma variedade de vulnerabilidades:
- **DNS Recursion**: Vulnerabilidade que permite ataques de DDoS através de recursão DNS.
  
- **NTP DDOS Amplification**: Amplificação DDOS em servidores NTP expostos.
  
- **NetBIOS Exposure**: Exposição de informações sensíveis devido a configurações inadequadas do NetBIOS

- **SNMP Public Community**: Exposição de comunidades SNMP públicas, permitindo potencial manipulação e ataques de DDoS.

- **Samba Outdated Version**: Vulnerabilidade em versões desatualizadas do Samba, suscetíveis a escaneamento de portas e acessos não autorizados.

- **Exposed MySQL**: Exposição do serviço MySQL, permitindo conexões não autenticadas que podem ser exploradas.

- **Redis No Authentication**: Exposição do Redis sem autenticação, permitindo acesso não autorizado ao serviço.

- **Exposed SSDP**: Exposição indevida do serviço SSDP, que deve operar apenas em ambientes locais.

- **Exposed Memcached**: Exposição do serviço Memcached à internet, criando uma vulnerabilidade.

- **Exposed SLP**: Exposição do serviço SLP à internet, quando deveria ser utilizado apenas localmente.

# 🧩 Entidades
Com base nas exigências e desafios apresentados pela RNP, a estrutura de entidades foi cuidadosamente projetada para suportar a complexidade e a diversidade dos sistemas monitorados. Abaixo está uma descrição das principais entidades:

- **Addresses**: Armazena endereços físicos das instituições, facilitando o mapeamento geográfico e a gestão física dos recursos de TI.

- **Institutions**: Contém informações detalhadas sobre as instituições associadas à RNP, incluindo nome e contato. Essa entidade permite o agrupamento de servidores e serviços sob uma mesma organização, fornecendo uma visão centralizada e hierárquica dos ativos monitorados.

- **Servers**: Armazena os servidores associados a cada instituição. Cada servidor inclui detalhes como nome, descrição, e estado de saúde, possibilitando a gestão e monitoramento da infraestrutura digital em diferentes instituições.

- **Services**: Contém informações sobre os serviços que estão em execução em cada servidor, incluindo detalhes técnicos como IP, porta, e status. Esses dados são fundamentais para monitorar a exposição e o comportamento dos serviços de rede.

- **Protocols**: Lista os protocolos suportados pelos serviços, como HTTP, FTP, SSH, etc. A identificação dos protocolos é crucial para entender as vias de comunicação e possíveis vetores de ataque.

- **Vulnerability Types**: Armazena os diferentes tipos de vulnerabilidades que a aplicação pode testar, baseando-se nos cenários mais críticos enfrentados pela RNP. Isso permite uma abordagem específica e eficaz na detecção de ameaças.

- **Vulnerabilities**: Contém os registros de vulnerabilidades identificadas, vinculando cada vulnerabilidade a um servidor e serviço específicos. Essa entidade é vital para a gestão de riscos e a priorização de ações corretivas.

- **Vulnerability History**: Mantém o histórico de alterações e resoluções das vulnerabilidades, permitindo que a RNP acompanhe a evolução e a mitigação de riscos ao longo do tempo. Isso também suporta auditorias de segurança e compliance.

# 🧪 Exemplo de Teste - Vulnerabilidade Redis
Um exemplo prático de teste realizado pela aplicação envolve o serviço Redis. Dois ambientes são configurados: um vulnerável, onde o Redis está exposto sem autenticação, e um ambiente seguro, onde o Redis está protegido por uma senha forte.

## Como realizar o Teste
No painel da aplicação, vá até a seção de Teste de Vulnerabilidades.
Selecione as seguintes vulnerabilidades para testar o ambiente Redis configurado com Docker:
  - **Redis Sem Autenticação - Ambiente Vulnerável**: Este teste simula um ambiente Redis vulnerável, sem autenticação.
  - **Redis Com Autenticação - Ambiente Seguro**: Este teste simula um ambiente Redis seguro, com autenticação configurada.
Execute os testes e verifique os resultados gerados pela aplicação.

Além disso, se preferir, você pode realizar o teste manualmente inserindo diretamente o IP e a porta do serviço Redis no formulário de teste:
- Para testar o Ambiente Seguro, insira o IP 172.30.1.4 e a porta 6378.
- Para testar o Ambiente Vulnerável, insira o IP 172.30.1.3 e a porta 6379.

## Resultado do Teste
- **Ambiente Vulnerável**: A aplicação detecta a falta de autenticação e classifica o ambiente como VULNERABLE.
- **Ambiente Seguro**: O Redis está configurado corretamente, e a aplicação classifica o ambiente como NOT VULNERABLE.

O usuário pode baixar os resultados do teste são apresentados em um relatório PDF, incluindo detalhes como o IP, a porta do serviço testado, a data do teste, o status, e uma explicação detalhada da importância de corrigir a vulnerabilidade. Além de gráficos relacionados a vulnerabilidades das instituições e servidores.
<br>

<b>📋 Exemplo de relatório:</b> [clique aqui](https://github.com/matheusvidal21/RedeSegura/blob/main/src/main/resources/static/files/exemplo_de_relatorio.pdf)

# 🔧 Como executar o projeto
### Pré-requisitos
- Java 21
- Maven
- Docker (necessário para simular os ambientes vulneráveis e seguros para teste)

### Passo a Passo
1. Clone o repositório da Rede Segura:
```bash
git clone https://github.com/seu-usuario/rede-segura.git
cd rede-segura
```
2. Compile e instale as dependências do projeto usando Maven:
```bash
mvn clean install
```
3. Construa e inicie os containers Docker:
```bash
docker-compose up --build
```
4. Execute a aplicação:
```bash
mvn spring-boot:run
```
5. Acesse a aplicação e teste vulnerabilidades:
- Abra o navegador e vá para `http://localhost:8080/rede-segura/` para acessar o painel principal da Rede Segura.
- No painel da aplicação, vá até a seção de teste de vulnerabilidades.
Selecione as seguintes vulnerabilidades para testar o ambiente Redis configurado com Docker:
- Redis Sem Autenticação - Ambiente Vulnerável:
  - Este teste simula um ambiente Redis vulnerável, sem autenticação.
  - Serviço: Redis Vulnerable (IP: 172.30.1.3, Porta: 6379).
- Redis Com Autenticação - Ambiente Seguro:
  - Este teste simula um ambiente Redis seguro, com autenticação configurada.
  - Serviço: Redis Secure (IP: 172.30.1.4, Porta: 6378).
Execute os testes e verifique os resultados gerados pela aplicação.

# 👥 Autores
<p align="center">
  <img src="src/main/resources/static/images/grupo.jpg" alt="Grupo" height="250">
</p>
Parabéns a todo o grupo pela dedicação e inovação demonstradas ao longo do desafio. Este hackathon foi, sem dúvida, um marco em nossa jornada :)
