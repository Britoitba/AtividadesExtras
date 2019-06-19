-- phpMyAdmin SQL Dump
-- version 4.6.5.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: 24-Jun-2017 às 04:27
-- Versão do servidor: 10.1.21-MariaDB
-- PHP Version: 7.1.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `atividadesextras`
--

-- --------------------------------------------------------

--
-- Estrutura da tabela `administradores`
--

CREATE TABLE `administradores` (
  `id` int(11) NOT NULL,
  `nome` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estrutura da tabela `alunos`
--

CREATE TABLE `alunos` (
  `id` int(11) NOT NULL,
  `nome` varchar(100) NOT NULL,
  `curso` varchar(50) NOT NULL,
  `ra` varchar(15) NOT NULL,
  `campus` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estrutura da tabela `atividades`
--

CREATE TABLE `atividades` (
  `id` int(11) NOT NULL,
  `nome` varchar(1000) DEFAULT NULL,
  `modalidade` varchar(1000) DEFAULT NULL,
  `descricao` varchar(1000) DEFAULT NULL,
  `cargaHoraria` int(11) DEFAULT NULL,
  `observacoes` varchar(1000) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `atividades`
--

INSERT INTO `atividades` (`id`, `nome`, `modalidade`, `descricao`, `cargaHoraria`, `observacoes`) VALUES
(1, 'Monitoria', 'Atividades de Ensino', 'Participação em atividade de monitoria remunerada ou voluntária em unidades curriculares ou laboratórios', 50, 'Serão consideradas atividades de monitoria aquelas desenvolvidas em consonância com o regulamento específico, aprovado pelos órgãos superiores competentes.'),
(2, 'Disciplinas extracurriculares', 'Atividades de Ensino', 'Realização de disciplinas em outros\ncursos, em outras instituições ou na\ninstituição de origem (somente em\náreas afins), com aprovação', 40, '--------------------------'),
(3, 'Participação em órgãos\ncolegiados / conselhos', 'Atividades de Ensino', 'Membro de órgãos colegiados:\nColegiado de Curso – Conselhos /\nórgãos colegiados existentes na\ninstituição – Diretório Acadêmico', 20, 'Serão consideradas participações em\nconselhos / comissões / órgãos\ncolegiados em que a representação\ndiscente faça parte de sua composição.\nSerá considerada, no máximo, 1 (uma)\natividade por semestre.'),
(4, 'Integrante de Núcleos ou\nGrupos de Estudos', 'Atividades de Ensino', 'Participação em núcleos ou grupos de\nestudos criados na instituição em áreas\nafins', 20, 'Serão consideradas as participações em\nnúcleos ou grupos de estudos, desde que\naprovado pelo campus.'),
(5, 'Cursos e minicursos', 'Atividades de Ensino', 'Participação em cursos e minicursos nas áreas correlatas', 40, 'Serão consideradas no máximo 2 (duas)\natividades, sendo que cada participação\ncorresponderá a 50% da carga horária\nsemestral correspondente a esta atividade.'),
(6, 'Cursos de Idiomas', 'Atividades de Ensino', 'Participação em cursos de língua estrangeira.', 20, 'Serão considerados até dois cursos oferecidos por instituições devidamente autorizadas.'),
(7, 'Palestras', 'Atividades de Ensino', 'Organização ou participação (ouvinte)\nem palestras nas áreas de atuação do\ncurso ', 20, 'Serão consideradas no máximo 2 (duas)\npalestras, sendo que cada palestra\ncorresponderá a 50% da carga horária\nsemestral destinada a esta atividade.'),
(8, 'Projetos de ensino', 'Atividades de Ensino', 'Participação em projetos vinculados\naos programas de incentivo às\nlicenciaturas (PIBID e outros);\nPrograma de Educação Tutorial (PET)', 60, 'Serão consideradas as participações,\nremunerada ou voluntária, em projetos,\ndesde que os mesmos constem de edital\npróprio.'),
(9, 'Atividades técnico-científicas', 'Atividades de Pesquisa', 'Participação em: simpósio, congresso,\nsemana de curso, workshop, dia de\ncampo, seminário, encontro, ciclo de\ndebate, ciclo de palestra e similares,\nsem apresentação de trabalhos', 40, 'SSerão consideradas as participações em\neventos na área do curso.\nSerão consideradas, no máximo, 2\n(duas) atividades, sendo que cada\natividade corresponderá a 50% da carga\nhorária semestral destinada a esta\natividade.'),
(10, 'Projetos de pesquisa e/ou\ninovação (iniciação científica)', 'Atividades de Pesquisa', 'Participação em projetos de pesquisa\ne/ou com bolsa de Iniciação Científica\nou em desenvolvimento de projeto de\npesquisa no Programa voluntário de\niniciação científica', 60, 'Serão consideradas participações em\nprojetos que constem de cadastros e\naprovação na coordenação de pesquisa\ndo campus.'),
(11, 'Publicação de artigos', 'Atividades de Pesquisa', 'Publicação de artigo em: simpósio,\ncongresso, revista científica ou jornais\nna área de atuação', 40, 'Serão consideradas, no máximo, 2\n(duas) publicações, sendo que cada\npublicação corresponderá a 50% da\ncarga horária semestral destinada a esta\natividade.'),
(12, 'Publicação de livros ou\ncapítulo de livro', 'Atividades de Pesquisa', 'Publicação de livros ou capítulo de\nlivros na área de atuação', 40, 'Serão consideradas, no máximo, 2\n(duas) publicações, sendo que cada\npublicação corresponderá a 50% da\ncarga horária semestral destinada a esta\natividade.'),
(13, 'Publicação em boletins\ntécnicos', 'Atividades de Pesquisa', 'Publicação em boletins técnicos ou\nsimilares na área de atuação', 30, 'Serão consideradas, no máximo, 2\n(duas) publicações, sendo que cada\npublicação corresponderá a 50% da\ncarga horária semestral destinada a esta\natividade.'),
(14, 'Atividades de extensão', 'Atividades de Extenção', 'Participação em projetos de extensão,\nou em assistência a projetos e\nprogramas sociais (sem bolsa)', 50, 'Serão considerados projetos cadastrados\nna instituição responsável pelo mesmo e\nque atendam a regulamento próprio.'),
(15, 'Programa bolsas de extensão', 'Atividades de Extenção', 'Participação em projetos com bolsa de\nextensão.', 60, 'Serão consideradas participações em\nprojetos cadastrados na instituição\nresponsável pelo mesmo e que atendam\na regulamento próprio.'),
(16, 'Programas de bolsas\ninstitucionais', 'Atividades de Extenção', 'Bolsista Institucional: bolsas de\ndemanda social ou complementação\neducacional', 40, 'Serão considerados os programas\ncadastrados em órgão responsável no\ncampus e que atendam a regulamento\npróprio.'),
(17, 'Estágios extracurriculares', 'Atividades de Extenção', 'Realização de estágios\nextracurriculares na instituição de\norigem ou em Instituições / empresas\npúblicas e privadas', 40, 'Serão considerados estágios\nextracurriculares que atendam ao\nregulamento próprio. Excetua-se o\nestágio supervisionado obrigatório.\nSerá considerado como Atividade\nComplementar o estágio com carga\nhorária mínima de 60 horas.'),
(18, 'Atuação profissional', 'Atividades de Extenção', 'Exercício de atividade profissional', 50, 'Serão consideradas as atividades profissionais na área do curso\ndevidamente comprovadas por carteira\nde trabalho assinada pelo empregador\nou declaração emitida por órgão\ncompetente no caso de servidor público\nque não for do regime CLT\n(Consolidação das Leis do Trabalho).'),
(19, 'Palestras proferidas', 'Atividades de Extenção', 'Palestrante em eventos', 20, 'Serão consideradas palestras que não\nsejam vinculadas às disciplinas\nregulares do curso, realizadas no\npróprio campus ou em outra instituição.\nSerão consideradas, no máximo, 2\n(duas) palestras, sendo que cada palestra\ncorresponderá a 50% da carga horária\nsemestral destinada a esta atividade.'),
(20, 'Expositor em eventos', 'Atividades de Extenção', 'Participação como expositor em\ncongressos, seminários e outros', 20, 'Serão consideradas as participações que\nconstarem de acompanhamento /\norientação de professor(es) da\ninstituição.'),
(21, 'Apresentação de trabalhos', 'Atividades de Extenção', 'Apresentação de trabalhos em\ncongressos, seminários e outros', 20, 'Serão consideradas as participações que\nconstarem de acompanhamento /\norientação de professor(es) da\ninstituição.'),
(22, 'Empresa Júnior e incubadoras', 'Atividades de Extenção', 'Participação em empresa júnior na\nárea do curso; participação em\nincubadora de empresa', 30, 'Serão consideradas as participações na\nárea do curso e que constarem de\nacompanhamento e orientação de\nprofessor(es) da instituição.'),
(23, 'Organização de eventos', 'Atividades de Extenção', 'Organização de eventos de pesquisa,\nextensão ou artístico-culturais', 20, 'Serão consideradas as participações que\nconstarem de acompanhamento e\norientação de professor(es) da instituição.'),
(24, 'Participação em visitas\ntécnicas', 'Atividades de Extenção', 'Participação em visitas técnicas\nrelacionadas ao curso', 20, 'Serão consideradas visitas técnicas\nregulares do curso, realizadas fora da\ninstituição.\nSerão consideradas, no máximo, 2\n(duas) visitas por semestre.'),
(25, 'Atividades artístico-culturais', 'Atividades Artistico-Culturais', 'Participação nas diversas atividades e\nmanifestações artísticas e culturais\noficiais', 50, 'Serão consideradas atividades que\ndifundam, valorizam e enriqueçam a\ncultura. As atividades deverão estar\noficializadas junto aos órgãos\ncompetentes do campus.\nSerão consideradas, no máximo, 2\n(duas) atividades, sendo cada uma\ncorrespondente a 50% da carga horária\nsemestral destinada a esta atividade.'),
(26, 'Atividades esportivas', 'Atividades Esportivas', 'Participação em atividades e/ou\nmodalidades esportivas oficiais', 20, 'Serão consideradas atividades que\nfavoreçam a integração das diversas\ndimensões e agentes do processo\neducativo. As atividades deverão estar\noficializadas junto aos órgãos\ncompetentes do campus.\nSerão consideradas, no máximo, 2\n(duas) atividades, sendo que cada\ncorresponderá a 50% da carga horária\nsemestral destinada a esta atividade.'),
(27, 'atividades sociais e ambientais', 'Atividades Sociais e Ambientais', 'Participação em atividades sociais\ne/ou ambientais oficiais', 20, 'Serão consideradas atividades que\nfavoreçam a integração das diversas dimensões e agentes do processo\neducativo. As atividades deverão estar\noficializadas junto aos órgãos\ncompetentes do campus.\nSerão consideradas, no máximo, 2\n(duas) atividades, sendo que cada\ncorresponderá a 50% da carga horária\nsemestral destinada a esta atividade.');

-- --------------------------------------------------------

--
-- Estrutura da tabela `coordenadores`
--

CREATE TABLE `coordenadores` (
  `id` int(11) NOT NULL,
  `nome` varchar(100) NOT NULL,
  `curso` varchar(50) NOT NULL,
  `cpf` varchar(15) NOT NULL,
  `campus` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estrutura da tabela `cursos`
--

CREATE TABLE `cursos` (
  `id` int(11) NOT NULL,
  `nome` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estrutura da tabela `professores`
--

CREATE TABLE `professores` (
  `id` int(11) NOT NULL,
  `nome` varchar(100) NOT NULL,
  `cpf` varchar(15) NOT NULL,
  `campus` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estrutura da tabela `requisicoes`
--

CREATE TABLE `requisicoes` (
  `id` int(11) NOT NULL,
  `atividade` varchar(100) DEFAULT NULL,
  `modalidade` varchar(100) DEFAULT NULL,
  `descricao` varchar(500) DEFAULT NULL,
  `ch` int(11) DEFAULT NULL,
  `chValidada` int(11) DEFAULT NULL,
  `local` varchar(100) DEFAULT NULL,
  `semestre` int(11) DEFAULT NULL,
  `ano` int(11) DEFAULT NULL,
  `relatorio` varchar(1000) DEFAULT NULL,
  `parecer` varchar(500) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `comprovante` varchar(100) DEFAULT NULL,
  `filecomprovante` mediumblob NOT NULL,
  `recurso` varchar(500) DEFAULT NULL,
  `idAluno` int(11) DEFAULT NULL,
  `idProfessor` int(11) DEFAULT NULL,
  `idCoordenador` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estrutura da tabela `usuarios`
--

CREATE TABLE `usuarios` (
  `id` int(11) NOT NULL,
  `email` varchar(100) NOT NULL,
  `senha` varchar(100) NOT NULL,
  `tipoUsuario` varchar(30) NOT NULL,
  `ativo` int(10) NOT NULL,
  `idAluno` int(11) DEFAULT NULL,
  `idCoordenador` int(11) DEFAULT NULL,
  `idProfessor` int(11) DEFAULT NULL,
  `idAdmin` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `administradores`
--
ALTER TABLE `administradores`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `alunos`
--
ALTER TABLE `alunos`
  ADD PRIMARY KEY (`id`) USING BTREE;

--
-- Indexes for table `atividades`
--
ALTER TABLE `atividades`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `coordenadores`
--
ALTER TABLE `coordenadores`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `cursos`
--
ALTER TABLE `cursos`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `professores`
--
ALTER TABLE `professores`
  ADD PRIMARY KEY (`id`) USING BTREE;

--
-- Indexes for table `requisicoes`
--
ALTER TABLE `requisicoes`
  ADD PRIMARY KEY (`id`),
  ADD KEY `requisicoes_ibfk_1` (`idAluno`),
  ADD KEY `requisicoes_ibfk_2` (`idProfessor`),
  ADD KEY `requisicoes_ibfk_3` (`idCoordenador`);

--
-- Indexes for table `usuarios`
--
ALTER TABLE `usuarios`
  ADD PRIMARY KEY (`id`) USING BTREE,
  ADD UNIQUE KEY `idadmin` (`idAdmin`),
  ADD KEY `idAluno` (`idAluno`),
  ADD KEY `idCoordenador` (`idCoordenador`),
  ADD KEY `idProfessor` (`idProfessor`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `administradores`
--
ALTER TABLE `administradores`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `alunos`
--
ALTER TABLE `alunos`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `atividades`
--
ALTER TABLE `atividades`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=28;
--
-- AUTO_INCREMENT for table `coordenadores`
--
ALTER TABLE `coordenadores`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `cursos`
--
ALTER TABLE `cursos`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `professores`
--
ALTER TABLE `professores`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `requisicoes`
--
ALTER TABLE `requisicoes`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `usuarios`
--
ALTER TABLE `usuarios`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- Constraints for dumped tables
--

--
-- Limitadores para a tabela `requisicoes`
--
ALTER TABLE `requisicoes`
  ADD CONSTRAINT `requisicoes_ibfk_1` FOREIGN KEY (`idAluno`) REFERENCES `alunos` (`id`),
  ADD CONSTRAINT `requisicoes_ibfk_2` FOREIGN KEY (`idProfessor`) REFERENCES `professores` (`id`),
  ADD CONSTRAINT `requisicoes_ibfk_3` FOREIGN KEY (`idCoordenador`) REFERENCES `coordenadores` (`id`);

--
-- Limitadores para a tabela `usuarios`
--
ALTER TABLE `usuarios`
  ADD CONSTRAINT `usuarios_ibfk_1` FOREIGN KEY (`idAluno`) REFERENCES `alunos` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `usuarios_ibfk_2` FOREIGN KEY (`idCoordenador`) REFERENCES `coordenadores` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `usuarios_ibfk_3` FOREIGN KEY (`idProfessor`) REFERENCES `professores` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `usuarios_ibfk_4` FOREIGN KEY (`idAdmin`) REFERENCES `administradores` (`id`) ON DELETE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
