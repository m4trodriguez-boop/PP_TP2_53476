# PP_TP2_53476
Trabajo Práctico 2 - Paradigmas de Programación

Gestión de Eventos Universitarios

Implementación en Java del sistema de eventos universitarios solicitado en los ejercicios 1, 2, 3 y 4.

Lenguaje: Java.

Versión recomendada: Java 17 o superior.

Paradigma: Programación Orientada a Objetos.

Sin dependencias externas: el proyecto utiliza únicamente la biblioteca estándar de Java.

El programa fue desarrollado en el IDE IntelliJ IDEA El programa se ejecuta en la clase App y busca generar como resultado:

Crear eventos, salas, estudiantes y actividades (Charla, Taller y Curso).

Inscribir estudiantes y controlar que no se supere el cupo.

Guardar y recuperar eventos mediante serialización.

Generar certificados para talleres y cursos.

Filtrar las actividades por tipo y calcular el costo de materiales usando genéricos.

Confirmar inscripciones y generar un ticket de acceso únicamente para las confirmadas.

Utilizar un hilo separado para enviar los tickets mientras el programa principal continúa mostrando información.

------------------------------- CAPTURA DE LA RESPUESTA DE LA CONSOLA-----------------------------------------

Estudiantes:--------------------------------------------------------------------------------------

El legajo es 53421

El nombre es Marcos

El legajo es 53234

El nombre es Luana

El legajo es 53345

El nombre es Leonel

El legajo es 53111

El nombre es Sofía



Eventos:----------------------------------------------------------------------------------------------

Eventos creados: 2



Salas:-------------------------------------------------------------------------------------

Sala de Jornadas de Programación: 1 - Aula Magna

Sala de Jornadas de Tecnología: 2 - Laboratorio 2



Actividades:-------------------------------------------------------------------------------------

Actividades creadas para cada evento:

El id es 1

El título es Java Orientado a Objetos

El cupo máximo es 30

Es de tipo Curso

Cantidad de inscriptos: 0

El id es 2

El título es Patrones de Diseño

El cupo máximo es 30

Es de tipo Taller

Cantidad de inscriptos: 0

El id es 3

El título es El futuro de la programación

El cupo máximo es 50

Es de tipo Charla

Cantidad de inscriptos: 0

El id es 4

El título es Bases de Datos

El cupo máximo es 30

Es de tipo Curso

Cantidad de inscriptos: 0

El id es 5

El título es Git y GitHub

El cupo máximo es 30

Es de tipo Taller

Cantidad de inscriptos: 0

El id es 6

El título es Innovación tecnológica

El cupo máximo es 50

Es de tipo Charla

Cantidad de inscriptos: 0



Inscripciones:-------------------------------------------------------------------------------------

Inscripción realizada: Marcos (Legajo: 53421) -> Curso 'Java Orientado a Objetos'.

Inscripción realizada: Luana (Legajo: 53234) -> Curso 'Java Orientado a Objetos'.

Inscripción realizada: Marcos (Legajo: 53421) -> Taller 'Patrones de Diseño'.

Inscripción realizada: Leonel (Legajo: 53345) -> Taller 'Patrones de Diseño'.

Inscripción realizada: Sofía (Legajo: 53111) -> Charla 'El futuro de la programación'.

Inscripción realizada: Luana (Legajo: 53234) -> Curso 'Bases de Datos'.

Inscripción realizada: Leonel (Legajo: 53345) -> Curso 'Bases de Datos'.

Inscripción realizada: Leonel (Legajo: 53345) -> Taller 'Git y GitHub'.

Inscripción realizada: Sofía (Legajo: 53111) -> Taller 'Git y GitHub'.

Inscripción realizada: Marcos (Legajo: 53421) -> Charla 'Innovación tecnológica'.



Detalle de inscripciones del evento 1:

-- Curso: Java Orientado a Objetos

La cantidad de inscriptos es: 2

Los inscriptos son:

Estudiante: Marcos (Legajo: 53421)

Fecha: 2026-09-21

Estado: confirmada

Estudiante: Luana (Legajo: 53234)

Fecha: 2026-09-21

Estado: confirmada

-- Taller: Patrones de Diseño

La cantidad de inscriptos es: 2

Los inscriptos son:

Estudiante: Marcos (Legajo: 53421)

Fecha: 2026-09-21

Estado: confirmada

Estudiante: Leonel (Legajo: 53345)

Fecha: 2026-09-21

Estado: confirmada

-- Charla: El futuro de la programación

La cantidad de inscriptos es: 1

Los inscriptos son:

Estudiante: Sofía (Legajo: 53111)

Fecha: 2026-09-21

Estado: confirmada



Certificados:-------------------------------------------------------------------------------------

Se emiten certificados solamente para Cursos y Talleres.

Certificados emitidos en evento 1: 4

Certificados emitidos en evento 2: 4



Certificados emitidos:-------------------------------------------------------------------------------------

Certificados emitidos en el evento Jornadas de Programación:

  CERTIFICADO DE ASISTENCIA | UTN-FRM | Estudiante: Marcos | Legajo: 53421 | Actividad: Java Orientado a Objetos | Tipo: Curso | 
  Nivel: 2
  
  CERTIFICADO DE ASISTENCIA | UTN-FRM | Estudiante: Luana | Legajo: 53234 | Actividad: Java Orientado a Objetos | Tipo: Curso | 
  Nivel: 2
  
  CERTIFICADO DE ASISTENCIA | UTN-FRM | Estudiante: Marcos | Legajo: 53421 | Actividad: Patrones de Diseño | Tipo: Taller
  
  CERTIFICADO DE ASISTENCIA | UTN-FRM | Estudiante: Leonel | Legajo: 53345 | Actividad: Patrones de Diseño | Tipo: Taller
  
Certificados emitidos en el evento Jornadas de Tecnología:

  CERTIFICADO DE ASISTENCIA | UTN-FRM | Estudiante: Luana | Legajo: 53234 | Actividad: Bases de Datos | Tipo: Curso | Nivel: 1
  
  CERTIFICADO DE ASISTENCIA | UTN-FRM | Estudiante: Leonel | Legajo: 53345 | Actividad: Bases de Datos | Tipo: Curso | Nivel: 1
  
  CERTIFICADO DE ASISTENCIA | UTN-FRM | Estudiante: Leonel | Legajo: 53345 | Actividad: Git y GitHub | Tipo: Taller
  
  CERTIFICADO DE ASISTENCIA | UTN-FRM | Estudiante: Sofía | Legajo: 53111 | Actividad: Git y GitHub | Tipo: Taller



Datos de los eventos:-------------------------------------------------------------------------------------

Evento: Jornadas de Programación (ID: EV-01)

Costo estimado: 24200.0

Sala asignada: 1 - Aula Magna

Cantidad de actividades: 3

  - Curso: Java Orientado a Objetos | inscriptos: 2
  
  - Taller: Patrones de Diseño | inscriptos: 2
  
  - Charla: El futuro de la programación | inscriptos: 1
    
Evento: Jornadas de Tecnología (ID: EV-02)

Costo estimado: 16940.0

Sala asignada: 2 - Laboratorio 2

Cantidad de actividades: 3

  - Curso: Bases de Datos | inscriptos: 2
  
  - Taller: Git y GitHub | inscriptos: 2
  
  - Charla: Innovación tecnológica | inscriptos: 1



Excepciones y persistencia:-------------------------------------------------------------------------------------



Control de cupos:-------------------------------------------------------------------------------------

Inscripción realizada: Marcos (Legajo: 53421) -> Taller 'Actividad de prueba de cupo'.

Inscripción rechazada: No se puede inscribir a Luana (Legajo: 53234): se alcanzó el cupo máximo de 1.



Persistencia:-------------------------------------------------------------------------------------

Inscripción rechazada: No se puede inscribir a Leonel (Legajo: 53345): se alcanzó el cupo máximo de 1.

Persistiendo evento en: /home/matias/Descargas/PP_TP1_53476-master/evento_serializado.dat

Evento persistido correctamente.

Leyendo evento persistido...

Evento leído correctamente.

Datos del evento recuperado:

Evento: Evento de Persistencia (ID: EV-03)

Costo estimado: 8470.0

Sala asignada: 3 - Aula 3

Cantidad de actividades: 1

  - Taller: Actividad de prueba de cupo | inscriptos: 1
    
El evento recuperado conserva sus actividades e inscripciones.



Filtrado y costo de materiales:-------------------------------------------------------------------------------------



Cantidad de actividades por tipo:-------------------------------------------------------------------------------------

Evento: Jornadas de Programación

  Charlas: 1
  
  Talleres: 1
  
  Cursos: 1
  
Evento: Jornadas de Tecnología

  Charlas: 1
  
  Talleres: 1
  
  Cursos: 1



Actividades filtradas:-------------------------------------------------------------------------------------

Charla -> cantidad: 1

  - Charla: El futuro de la programación
    
Taller -> cantidad: 1

  - Taller: Patrones de Diseño
    
Curso -> cantidad: 1

  - Curso: Java Orientado a Objetos
    
Charla -> cantidad: 1

  - Charla: Innovación tecnológica
    
Taller -> cantidad: 1

  - Taller: Git y GitHub
    
Curso -> cantidad: 1

  - Curso: Bases de Datos



Costo de materiales por tipo:-------------------------------------------------------------------------------------

Evento: Jornadas de Programación

  Costo de Charlas: 0.0
  
  Costo de Talleres: 5000.0
  
  Costo de Cursos: 5000.0
  
Evento: Jornadas de Tecnología

  Costo de Charlas: 0.0
  
  Costo de Talleres: 2000.0
  
  Costo de Cursos: 4000.0
  
Costo total de materiales de Jornadas de Programación usando List<Actividad>: 10000.0



Tickets y concurrencia:-------------------------------------------------------------------------------------



Estudiantes:-------------------------------------------------------------------------------------

El legajo es 53421

El nombre es Marcos

El legajo es 53234

El nombre es Luana

El legajo es 53345

El nombre es Leonel

El legajo es 53111

El nombre es Sofía



Eventos, salas y actividades:-------------------------------------------------------------------------------------

Evento: Congreso de Software (ID: EV-04)

Costo estimado: 27830.0

Sala asignada: 4 - Auditorio Principal

Cantidad de actividades: 2

  - Curso: Java Avanzado | inscriptos: 0
  
  - Taller: Testing Automatizado | inscriptos: 0

Evento: Jornada de Tecnología (ID: EV-05)

Costo estimado: 16940.0

Sala asignada: 5 - Aula 5

Cantidad de actividades: 2

  - Curso: Bases de Datos II | inscriptos: 0
  
  - Charla: Novedades de IA | inscriptos: 0



Inscripciones:-------------------------------------------------------------------------------------

Inscripción pendiente: Marcos (Legajo: 53421) -> Curso 'Java Avanzado'.

Inscripción pendiente: Luana (Legajo: 53234) -> Curso 'Java Avanzado'.

Inscripción pendiente: Leonel (Legajo: 53345) -> Taller 'Testing Automatizado'.

Inscripción pendiente: Sofía (Legajo: 53111) -> Curso 'Bases de Datos II'.

Inscripción pendiente: Marcos (Legajo: 53421) -> Curso 'Bases de Datos II'.



Confirmación de inscripciones:-------------------------------------------------------------------------------------

Confirmadas: Marcos (Legajo: 53421), Leonel (Legajo: 53345) y Sofía (Legajo: 53111)

Pendientes: Luana (Legajo: 53234) y Marcos (Legajo: 53421)



Generación de tickets:-------------------------------------------------------------------------------------

Ticket generado: TKT-53421-7 | Emisión: 2026-09-21 | Estudiante: Marcos (Legajo: 53421) | Actividad: Java Avanzado

Ticket generado: TKT-53345-8 | Emisión: 2026-09-21 | Estudiante: Leonel (Legajo: 53345) | Actividad: Testing Automatizado

Ticket generado: TKT-53111-9 | Emisión: 2026-09-21 | Estudiante: Sofía (Legajo: 53111) | Actividad: Bases de Datos II

No se generó ticket para Luana (Legajo: 53234): No se puede generar un ticket: la inscripción de Luana (Legajo: 53234) no está 
confirmada.



Envío de tickets:-------------------------------------------------------------------------------------

Envío de tickets iniciado para: Congreso de Software

datos:-------------------------------------------------------------------------------------

Envío de tickets iniciado para: Jornada de Tecnología

Consulta 1 desde el hilo principal

Evento: Congreso de Software (ID: EV-04)

Costo estimado: 27830.0

Sala asignada: 4 - Auditorio Principal

Cantidad de actividades: 2

  - Curso: Java Avanzado | inscriptos: 2
  
  - Taller: Testing Automatizado | inscriptos: 1
    
Evento: Jornada de Tecnología (ID: EV-05)

Costo estimado: 16940.0

Sala asignada: 5 - Aula 5

Cantidad de actividades: 2

  - Curso: Bases de Datos II | inscriptos: 2
  
  - Charla: Novedades de IA | inscriptos: 0
    
Consulta 2 desde el hilo principal

Evento: Congreso de Software (ID: EV-04)

Costo estimado: 27830.0

Sala asignada: 4 - Auditorio Principal

Cantidad de actividades: 2

  - Curso: Java Avanzado | inscriptos: 2
  
  - Taller: Testing Automatizado | inscriptos: 1
    
Evento: Jornada de Tecnología (ID: EV-05)

Costo estimado: 16940.0

Sala asignada: 5 - Aula 5

Cantidad de actividades: 2

  - Curso: Bases de Datos II | inscriptos: 2
  
  - Charla: Novedades de IA | inscriptos: 0
    
Consulta 3 desde el hilo principal

Evento: Congreso de Software (ID: EV-04)

Costo estimado: 27830.0

Sala asignada: 4 - Auditorio Principal

Cantidad de actividades: 2

  - Curso: Java Avanzado | inscriptos: 2
  
  - Taller: Testing Automatizado | inscriptos: 1
Evento: Jornada de Tecnología (ID: EV-05)

Costo estimado: 16940.0

Sala asignada: 5 - Aula 5

Cantidad de actividades: 2

  - Curso: Bases de Datos II | inscriptos: 2
  
  - Charla: Novedades de IA | inscriptos: 0
Enviando TKT-53421-7 -> Marcos (Legajo: 53421) | Actividad: Java Avanzado

Enviando TKT-53111-9 -> Sofía (Legajo: 53111) | Actividad: Bases de Datos II

Tickets enviados para Jornada de Tecnología: 1

Enviando TKT-53345-8 -> Leonel (Legajo: 53345) | Actividad: Testing Automatizado

Tickets enviados para Congreso de Software: 2


Estado final de los tickets:-------------------------------------------------------------------------------------

TKT-53421-7 | Emisión: 2026-09-21 | Estudiante: Marcos (Legajo: 53421) | Actividad: Java Avanzado | enviado: true

TKT-53345-8 | Emisión: 2026-09-21 | Estudiante: Leonel (Legajo: 53345) | Actividad: Testing Automatizado | enviado: true

TKT-53111-9 | Emisión: 2026-09-21 | Estudiante: Sofía (Legajo: 53111) | Actividad: Bases de Datos II | enviado: true

-----------------------------------------------------------------------------------------------------------------------------

