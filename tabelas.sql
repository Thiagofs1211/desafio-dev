CREATE TABLE `pessoa_fisica` (
   `id` int NOT NULL AUTO_INCREMENT,
   `nome` varchar(50) NOT NULL,
   `cpf` varchar(11) NOT NULL,
   PRIMARY KEY (`id`),
   UNIQUE KEY `cpf_UNIQUE` (`cpf`)
 ) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci
 
 CREATE TABLE `estabelecimento` (
   `id` int NOT NULL AUTO_INCREMENT,
   `nome_estabelecimento` varchar(100) NOT NULL,
   `dono_estabelecimento` int NOT NULL,
   `cnpj` varchar(15) DEFAULT NULL,
   PRIMARY KEY (`id`),
   UNIQUE KEY `cnpj_UNIQUE` (`cnpj`),
   KEY `dono_estabelecimento` (`dono_estabelecimento`),
   CONSTRAINT `dono_estabelecimento` FOREIGN KEY (`dono_estabelecimento`) REFERENCES `pessoa_fisica` (`id`)
 ) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci

CREATE TABLE `transacao` (
   `id` int NOT NULL AUTO_INCREMENT,
   `data` date NOT NULL,
   `valor` double NOT NULL,
   `cartao` varchar(20) DEFAULT NULL,
   `hora` time NOT NULL,
   `estabelecimento` int DEFAULT NULL,
   `tipo` varchar(45) NOT NULL,
   PRIMARY KEY (`id`),
   KEY `transacao_estabelecimento_idx` (`estabelecimento`),
   CONSTRAINT `transacao_estabelecimento` FOREIGN KEY (`estabelecimento`) REFERENCES `estabelecimento` (`id`)
 ) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci