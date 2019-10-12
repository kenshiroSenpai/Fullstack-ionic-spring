drop database if exists db_list;
create database db_list;
use db_list;

CREATE TABLE IF NOT EXISTS `db_list`.`lists` (
  `id_list` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `list_name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id_list`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

-- -----------------------------------------------------
-- Table `db_list`.`products`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `db_list`.`products` (
  `id_product` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `product_name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id_product`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

-- -----------------------------------------------------
-- Table `db_list`.`lists_has_products`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `db_list`.`lists_has_products` (
  `id_list` BIGINT(20) NOT NULL,
  `id_product` BIGINT(20) NOT NULL,
  PRIMARY KEY (`id_list`, `id_product`),
  INDEX `fk_lists_has_products_products1_idx` (`id_product` ASC) VISIBLE,
  INDEX `fk_lists_has_products_lists_idx` (`id_list` ASC) VISIBLE,
  CONSTRAINT `fk_lists_has_products_lists`
    FOREIGN KEY (`id_list`)
    REFERENCES `db_list`.`lists` (`id_list`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_lists_has_products_products1`
    FOREIGN KEY (`id_product`)
    REFERENCES `db_list`.`products` (`id_product`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

insert into db_list.lists values(null,"list1");
insert into db_list.lists values(null,"list2");

insert into db_list.products values(null,"Banana");
insert into db_list.products values(null,"Manzana");
insert into db_list.products values(null,"Aguacate");
insert into db_list.products values(null,"Tomate");

insert into db_list.lists_has_products values(2,1);
insert into db_list.lists_has_products values(2,2);
insert into db_list.lists_has_products values(1,3);
insert into db_list.lists_has_products values(1,4);

select * from lists;
select * from products;
select * from lists_has_products;
