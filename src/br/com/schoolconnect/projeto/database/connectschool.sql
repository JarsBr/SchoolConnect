-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Tempo de geração: 21/06/2023 às 03:54
-- Versão do servidor: 10.4.28-MariaDB
-- Versão do PHP: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Banco de dados: `connectschool`
--

-- --------------------------------------------------------

--
-- Estrutura para tabela `aluno`
--

CREATE TABLE `aluno` (
  `matricula` int(11) NOT NULL,
  `id_curso_periodo` int(11) DEFAULT NULL,
  `nome` varchar(45) DEFAULT NULL,
  `password` varchar(45) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `data_inicio` varchar(45) DEFAULT NULL,
  `situacao` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `aluno`
--

INSERT INTO `aluno` (`matricula`, `id_curso_periodo`, `nome`, `password`, `email`, `data_inicio`, `situacao`) VALUES
(11, 1, 'Jose Admin', '11', 'JoseAdmin@gmail.com', '19/06/2023', 'Cursando'),
(111, 1, 'Lucas Silva', '11', 'LucasSilva@gmail.com', '19/06/2023', 'Cursando'),
(112, 1, 'Sofia Oliveira', '11', 'SofiaOliveira@gmail.com', '19/06/2023', 'Cursando');

-- --------------------------------------------------------

--
-- Estrutura para tabela `aluno_has_disciplina`
--

CREATE TABLE `aluno_has_disciplina` (
  `id_aluno` int(11) NOT NULL,
  `id_disciplina_ofertada` int(11) NOT NULL,
  `status` varchar(45) DEFAULT NULL,
  `nota` float DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `aluno_has_disciplina`
--

INSERT INTO `aluno_has_disciplina` (`id_aluno`, `id_disciplina_ofertada`, `status`, `nota`) VALUES
(112, 11101, 'Teste', 0),
(11, 11101, 'Teste', 0);

-- --------------------------------------------------------

--
-- Estrutura para tabela `disciplina`
--

CREATE TABLE `disciplina` (
  `cod_disciplina` int(11) NOT NULL,
  `nome` varchar(45) NOT NULL,
  `descricao` varchar(255) DEFAULT NULL,
  `conteudo` varchar(255) DEFAULT NULL,
  `carga_horaria` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `disciplina`
--

INSERT INTO `disciplina` (`cod_disciplina`, `nome`, `descricao`, `conteudo`, `carga_horaria`) VALUES
(101, 'Banco De Dados', 'Tecnologia que permite o armazenamento e a gestão organizada de dados', 'Conteúdo Programático · Revisão de SGBD relacional e SQL · Armazenamento de dados e indexação · Projeto de bancos de dados relacionais · Processamento de consultas.', '36'),
(102, 'Programação O.O.', 'Paradigma de programação baseado no conceito de \"objetos\"', 'Conteúdo Programático · Conceitos básicos de orientação a objetos · Encapsulamento, herança e polimorfismo · Tratamento de exceções · Princípios de design orientado a objetos.', '38'),
(103, 'Redes Computadores', 'Sistema de comunicação digital, guiados por um conjunto de regras.', 'Conteúdo Programático · Modelo de referência OSI e TCP/IP · Protocolos de transporte e aplicação · Arquiteturas de redes locais e distribuídas · Segurança em redes de computadores.', '24'),
(104, 'Inteligência Artificial', 'Programas computacionais capazes de reproduzir o comportamento humano', 'Conteúdo Programático · Agentes inteligentes · Busca heurística · Aprendizado de máquina · Redes neurais artificiais · Processamento de linguagem natural.', '24'),
(105, 'Sistemas Operacionais', 'Programas cuja função é gerenciar os recursos do sistema', 'Conteúdo Programático · Gerenciamento de processos e threads · Gerenciamento de memória · Sistemas de arquivos · Proteção e segurança de sistemas operacionais.', '60'),
(106, 'Desenvolvimento Web', 'Desenvolvimento de sites, na Internet ou numa intranet', 'Conteúdo Programático · HTML, CSS e JavaScript · Frameworks de desenvolvimento web · Bancos de dados para web · Arquitetura de aplicações web.', '48'),
(107, 'Matemática', 'Ciência do raciocínio lógico e abstrato', 'Matemática é a ciência do raciocínio lógico e abstrato, que estuda quantidades, espaço e medidas, estruturas, variações e estatística. Não há, porém, uma definição consensual por parte da comunidade científica.', '46');

-- --------------------------------------------------------

--
-- Estrutura para tabela `disciplina_ofertada`
--

CREATE TABLE `disciplina_ofertada` (
  `id_disciplina_ofertada` int(11) NOT NULL,
  `matricula_professor` int(11) DEFAULT NULL,
  `cod_disciplina` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `disciplina_ofertada`
--

INSERT INTO `disciplina_ofertada` (`id_disciplina_ofertada`, `matricula_professor`, `cod_disciplina`) VALUES
(11101, 212, 101),
(11102, 211, 102),
(11103, 212, 103),
(11104, 211, 104),
(11105, 212, 105),
(11106, 211, 106),
(11107, 22, 107);

-- --------------------------------------------------------

--
-- Estrutura para tabela `professor`
--

CREATE TABLE `professor` (
  `matricula` int(11) NOT NULL,
  `nome` varchar(45) DEFAULT NULL,
  `password` varchar(45) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `graus_academico` varchar(255) DEFAULT NULL,
  `curriculo` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `professor`
--

INSERT INTO `professor` (`matricula`, `nome`, `password`, `email`, `graus_academico`, `curriculo`) VALUES
(22, 'Italo Admin', '22', 'ItaloAdmin@gmail.com', 'Graduado \'Programação Super Avançada Plus 3\'', 'Criador do Java Criador do Java Criador do Java Criador do Java Criador do Java teste'),
(211, 'Lucas Silva', '22', 'LucasSilva@gmail.com', 'Bacharelado em Engenharia de Computação', 'Professor Novato'),
(212, 'Alice Rodrigues', '22', 'AliceRodrigues@gmail.com', 'Graduação em Ciências da Computação', 'Professor Experiente');

--
-- Índices para tabelas despejadas
--

--
-- Índices de tabela `aluno`
--
ALTER TABLE `aluno`
  ADD PRIMARY KEY (`matricula`);

--
-- Índices de tabela `aluno_has_disciplina`
--
ALTER TABLE `aluno_has_disciplina`
  ADD KEY `id_aluno_idx` (`id_aluno`),
  ADD KEY `id_disciplina_ofertada_idx` (`id_disciplina_ofertada`);

--
-- Índices de tabela `disciplina`
--
ALTER TABLE `disciplina`
  ADD PRIMARY KEY (`cod_disciplina`);

--
-- Índices de tabela `disciplina_ofertada`
--
ALTER TABLE `disciplina_ofertada`
  ADD PRIMARY KEY (`id_disciplina_ofertada`),
  ADD KEY `matricula_professor_idx` (`matricula_professor`),
  ADD KEY `cod_disciplina_idx` (`cod_disciplina`);

--
-- Índices de tabela `professor`
--
ALTER TABLE `professor`
  ADD PRIMARY KEY (`matricula`);

--
-- Restrições para tabelas despejadas
--

--
-- Restrições para tabelas `aluno_has_disciplina`
--
ALTER TABLE `aluno_has_disciplina`
  ADD CONSTRAINT `id_aluno` FOREIGN KEY (`id_aluno`) REFERENCES `aluno` (`matricula`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `id_disciplina_ofertada` FOREIGN KEY (`id_disciplina_ofertada`) REFERENCES `disciplina_ofertada` (`id_disciplina_ofertada`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Restrições para tabelas `disciplina_ofertada`
--
ALTER TABLE `disciplina_ofertada`
  ADD CONSTRAINT `cod_disciplina` FOREIGN KEY (`cod_disciplina`) REFERENCES `disciplina` (`cod_disciplina`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `matricula_professor` FOREIGN KEY (`matricula_professor`) REFERENCES `professor` (`matricula`) ON DELETE NO ACTION ON UPDATE NO ACTION;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
