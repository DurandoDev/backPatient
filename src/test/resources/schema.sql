CREATE TABLE IF NOT EXISTS `db_medilabo_test`.`patient` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `firstname` VARCHAR(45) NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `dateOfBirth` DATE NOT NULL,
  `gender` VARCHAR(45) NOT NULL,
  `address` VARCHAR(45) NULL,
  `phoneNumber` VARCHAR(45) NULL,
  PRIMARY KEY (`id`));
