DROP TABLE IF EXISTS CLIENT;

  CREATE TABLE CLIENT (
    id bigint(20) NOT NULL AUTO_INCREMENT,
    type varchar(250) NOT NULL,
    age bigint(5) NOT NULL,
    discount bigint,
    document_for_discount BOOLEAN,
    PRIMARY KEY (id)
    );
