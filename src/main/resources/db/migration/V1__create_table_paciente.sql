CREATE TABLE PACIENTE_TB(
    idPaciente serial PRIMARY KEY,
    nomePaciente VARCHAR(200),
    cpfPaciente VARCHAR(11),
    emailPaciente VARCHAR(200),
    telefonePaciente VARCHAR(20)

)