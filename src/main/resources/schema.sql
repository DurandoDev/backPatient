CREATE TABLE IF NOT EXISTS `db_medilabo`.`patient` (
  `id` INTEGER NOT NULL AUTO_INCREMENT,
  `firstname` VARCHAR(45) NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `date_of_birth` DATE NOT NULL,
  `gender` VARCHAR(45) NOT NULL,
  `address` VARCHAR(45) NULL,
  `phone_number` VARCHAR(45) NULL,
  PRIMARY KEY (`id`));
