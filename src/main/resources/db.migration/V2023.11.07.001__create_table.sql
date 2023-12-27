CREATE TABLE expert_functions
(
    id   INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE software_types
(
    id   INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE countries
(
    id   INT PRIMARY KEY ,
    name VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE literature
(
    id                  INT PRIMARY KEY AUTO_INCREMENT,
    name                VARCHAR(150) NOT NULL UNIQUE,
    author              VARCHAR(150) NOT NULL,
    description         TEXT,
    url                 TEXT,
    date_of_last_access DATE
);

CREATE TABLE environmental_monitoring_classifiers
(
    id   INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(150) NOT NULL UNIQUE
);

CREATE TABLE classifier_computer_system_types
(
    id   INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE systems
(
    id                                          INT PRIMARY KEY AUTO_INCREMENT,
    name                                        VARCHAR(150) NOT NULL UNIQUE,
    developer                                   VARCHAR(300) NOT NULL,
    availability_of_decision_making_classifiers BOOLEAN,
    release_date                                DATE,
    longitude                                   FLOAT,
    latitude                                    FLOAT,
    country_id                                  INT,
    software_type_id                            INT,
    FOREIGN KEY (country_id) REFERENCES countries (id),
    FOREIGN KEY (software_type_id) REFERENCES software_types (id)
);

CREATE TABLE system_literature
(
    system_id     INT,
    literature_id INT,
    PRIMARY KEY (system_id, literature_id),
    FOREIGN KEY (system_id) REFERENCES systems (id),
    FOREIGN KEY (literature_id) REFERENCES literature (id)
);

CREATE TABLE classifier_computer_systems
(
    id          INT PRIMARY KEY AUTO_INCREMENT,
    name        VARCHAR(150) NOT NULL UNIQUE,
    description TEXT,
    type_id     INT,
    FOREIGN KEY (type_id) REFERENCES classifier_computer_system_types (id)
);

CREATE TABLE literature_classifier_computer_system
(
    classifier_computer_system_id INT,
    literature_id                 INT,
    PRIMARY KEY (classifier_computer_system_id, literature_id),
    FOREIGN KEY (classifier_computer_system_id) REFERENCES classifier_computer_systems (id),
    FOREIGN KEY (literature_id) REFERENCES literature (id)
);




CREATE TABLE literature_system
(
    system_id     INT,
    literature_id INT,
    PRIMARY KEY (system_id, literature_id),
    FOREIGN KEY (system_id) REFERENCES systems (id),
    FOREIGN KEY (literature_id) REFERENCES literature (id)
);


CREATE TABLE system_expert_function
(
    system_id     INT,
    expert_function_id INT,
    PRIMARY KEY (system_id, expert_function_id),
    FOREIGN KEY (system_id) REFERENCES systems (id),
    FOREIGN KEY (expert_function_id) REFERENCES expert_functions (id)
);




CREATE TABLE system_classifier_computer_system
(
    system_id                     INT,
    classifier_computer_system_id INT,
    PRIMARY KEY (system_id, classifier_computer_system_id),
    FOREIGN KEY (system_id) REFERENCES systems (id),
    FOREIGN KEY (classifier_computer_system_id) REFERENCES classifier_computer_systems (id)
);

CREATE TABLE system_environmental_monitoring_classifier
(
    system_id                              INT,
    environmental_monitoring_classifier_id INT,
    PRIMARY KEY (system_id, environmental_monitoring_classifier_id),
    FOREIGN KEY (system_id) REFERENCES systems (id),
    FOREIGN KEY (environmental_monitoring_classifier_id) REFERENCES environmental_monitoring_classifiers (id)
);
