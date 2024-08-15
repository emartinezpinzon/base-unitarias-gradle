CREATE TABLE peliculas (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(255),
    descripcion VARCHAR(255),
    genero VARCHAR(150)
);

INSERT INTO peliculas (titulo, descripcion, genero) VALUES
('Inception', 'Un ladrón que roba secretos corporativos a través del uso de la tecnología de los sueños es dado la tarea de implantar una idea en la mente de un CEO.', 'Ciencia Ficción'),
('The Shawshank Redemption', 'La historia de un banquero que es condenado a cadena perpetua en la prisión de Shawshank por el asesinato de su esposa y su amante.', 'Drama');


