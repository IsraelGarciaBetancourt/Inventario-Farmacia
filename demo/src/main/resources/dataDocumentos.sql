-- ============================================
-- DATA PARA PROBAR DASHBOARD
-- Genera datos en los últimos 7 días, 7 semanas y 7 meses
-- ============================================

-- Fecha de hoy asumida: 2025-11-29

-- ============================================
-- ÚLTIMOS 7 DÍAS (del 23 al 29 de noviembre)
-- ============================================

-- Día 1: 23/11/2025
INSERT INTO documentos (id, tipo_movimiento, numero_documento, fecha, id_usuario, observacion, activo)
VALUES (100, 'INGRESO', 'ING-TEST-001', '2025-11-23', 1, 'Ingreso día 1', TRUE);
INSERT INTO documento_detalles (id_documento, id_producto_catalogo, cantidad, activo)
VALUES (100, 1, 50, TRUE), (100, 2, 30, TRUE);

INSERT INTO documentos (id, tipo_movimiento, numero_documento, fecha, id_usuario, observacion, activo)
VALUES (101, 'SALIDA', 'SAL-TEST-001', '2025-11-23', 1, 'Salida día 1', TRUE);
INSERT INTO documento_detalles (id_documento, id_producto_catalogo, cantidad, activo)
VALUES (101, 1, 10, TRUE), (101, 2, 5, TRUE);

-- Día 2: 24/11/2025
INSERT INTO documentos (id, tipo_movimiento, numero_documento, fecha, id_usuario, observacion, activo)
VALUES (102, 'INGRESO', 'ING-TEST-002', '2025-11-24', 1, 'Ingreso día 2', TRUE);
INSERT INTO documento_detalles (id_documento, id_producto_catalogo, cantidad, activo)
VALUES (102, 3, 40, TRUE);

INSERT INTO documentos (id, tipo_movimiento, numero_documento, fecha, id_usuario, observacion, activo)
VALUES (103, 'SALIDA', 'SAL-TEST-002', '2025-11-24', 1, 'Salida día 2', TRUE);
INSERT INTO documento_detalles (id_documento, id_producto_catalogo, cantidad, activo)
VALUES (103, 1, 15, TRUE);

-- Día 3: 25/11/2025
INSERT INTO documentos (id, tipo_movimiento, numero_documento, fecha, id_usuario, observacion, activo)
VALUES (104, 'INGRESO', 'ING-TEST-003', '2025-11-25', 1, 'Ingreso día 3', TRUE);
INSERT INTO documento_detalles (id_documento, id_producto_catalogo, cantidad, activo)
VALUES (104, 4, 60, TRUE);

INSERT INTO documentos (id, tipo_movimiento, numero_documento, fecha, id_usuario, observacion, activo)
VALUES (105, 'SALIDA', 'SAL-TEST-003', '2025-11-25', 1, 'Salida día 3', TRUE);
INSERT INTO documento_detalles (id_documento, id_producto_catalogo, cantidad, activo)
VALUES (105, 4, 20, TRUE);

-- Día 4: 26/11/2025
INSERT INTO documentos (id, tipo_movimiento, numero_documento, fecha, id_usuario, observacion, activo)
VALUES (106, 'INGRESO', 'ING-TEST-004', '2025-11-26', 1, 'Ingreso día 4', TRUE);
INSERT INTO documento_detalles (id_documento, id_producto_catalogo, cantidad, activo)
VALUES (106, 2, 35, TRUE);

INSERT INTO documentos (id, tipo_movimiento, numero_documento, fecha, id_usuario, observacion, activo)
VALUES (107, 'SALIDA', 'SAL-TEST-004', '2025-11-26', 1, 'Salida día 4', TRUE);
INSERT INTO documento_detalles (id_documento, id_producto_catalogo, cantidad, activo)
VALUES (107, 2, 12, TRUE);

-- Día 5: 27/11/2025
INSERT INTO documentos (id, tipo_movimiento, numero_documento, fecha, id_usuario, observacion, activo)
VALUES (108, 'INGRESO', 'ING-TEST-005', '2025-11-27', 1, 'Ingreso día 5', TRUE);
INSERT INTO documento_detalles (id_documento, id_producto_catalogo, cantidad, activo)
VALUES (108, 1, 45, TRUE);

INSERT INTO documentos (id, tipo_movimiento, numero_documento, fecha, id_usuario, observacion, activo)
VALUES (109, 'SALIDA', 'SAL-TEST-005', '2025-11-27', 1, 'Salida día 5', TRUE);
INSERT INTO documento_detalles (id_documento, id_producto_catalogo, cantidad, activo)
VALUES (109, 1, 18, TRUE), (109, 4, 25, TRUE);

-- Día 6: 28/11/2025
INSERT INTO documentos (id, tipo_movimiento, numero_documento, fecha, id_usuario, observacion, activo)
VALUES (110, 'INGRESO', 'ING-TEST-006', '2025-11-28', 1, 'Ingreso día 6', TRUE);
INSERT INTO documento_detalles (id_documento, id_producto_catalogo, cantidad, activo)
VALUES (110, 3, 55, TRUE);

INSERT INTO documentos (id, tipo_movimiento, numero_documento, fecha, id_usuario, observacion, activo)
VALUES (111, 'SALIDA', 'SAL-TEST-006', '2025-11-28', 1, 'Salida día 6', TRUE);
INSERT INTO documento_detalles (id_documento, id_producto_catalogo, cantidad, activo)
VALUES (111, 3, 22, TRUE);

-- Día 7: 29/11/2025 (HOY)
INSERT INTO documentos (id, tipo_movimiento, numero_documento, fecha, id_usuario, observacion, activo)
VALUES (112, 'INGRESO', 'ING-TEST-007', '2025-11-29', 1, 'Ingreso día 7', TRUE);
INSERT INTO documento_detalles (id_documento, id_producto_catalogo, cantidad, activo)
VALUES (112, 4, 70, TRUE);

INSERT INTO documentos (id, tipo_movimiento, numero_documento, fecha, id_usuario, observacion, activo)
VALUES (113, 'SALIDA', 'SAL-TEST-007', '2025-11-29', 1, 'Salida día 7', TRUE);
INSERT INTO documento_detalles (id_documento, id_producto_catalogo, cantidad, activo)
VALUES (113, 4, 30, TRUE), (113, 2, 8, TRUE);

-- ============================================
-- ÚLTIMAS 7 SEMANAS (de octubre a noviembre)
-- ============================================

-- Semana 1: 08-14 Oct
INSERT INTO documentos (id, tipo_movimiento, numero_documento, fecha, id_usuario, observacion, activo)
VALUES (114, 'INGRESO', 'ING-TEST-S1', '2025-10-10', 1, 'Ingreso semana 1', TRUE);
INSERT INTO documento_detalles (id_documento, id_producto_catalogo, cantidad, activo)
VALUES (114, 1, 100, TRUE);
INSERT INTO documentos (id, tipo_movimiento, numero_documento, fecha, id_usuario, observacion, activo)
VALUES (115, 'SALIDA', 'SAL-TEST-S1', '2025-10-12', 1, 'Salida semana 1', TRUE);
INSERT INTO documento_detalles (id_documento, id_producto_catalogo, cantidad, activo)
VALUES (115, 1, 35, TRUE);

-- Semana 2: 15-21 Oct
INSERT INTO documentos (id, tipo_movimiento, numero_documento, fecha, id_usuario, observacion, activo)
VALUES (116, 'INGRESO', 'ING-TEST-S2', '2025-10-17', 1, 'Ingreso semana 2', TRUE);
INSERT INTO documento_detalles (id_documento, id_producto_catalogo, cantidad, activo)
VALUES (116, 2, 80, TRUE);
INSERT INTO documentos (id, tipo_movimiento, numero_documento, fecha, id_usuario, observacion, activo)
VALUES (117, 'SALIDA', 'SAL-TEST-S2', '2025-10-19', 1, 'Salida semana 2', TRUE);
INSERT INTO documento_detalles (id_documento, id_producto_catalogo, cantidad, activo)
VALUES (117, 2, 28, TRUE);

-- Semana 3: 22-28 Oct
INSERT INTO documentos (id, tipo_movimiento, numero_documento, fecha, id_usuario, observacion, activo)
VALUES (118, 'INGRESO', 'ING-TEST-S3', '2025-10-24', 1, 'Ingreso semana 3', TRUE);
INSERT INTO documento_detalles (id_documento, id_producto_catalogo, cantidad, activo)
VALUES (118, 3, 90, TRUE);
INSERT INTO documentos (id, tipo_movimiento, numero_documento, fecha, id_usuario, observacion, activo)
VALUES (119, 'SALIDA', 'SAL-TEST-S3', '2025-10-26', 1, 'Salida semana 3', TRUE);
INSERT INTO documento_detalles (id_documento, id_producto_catalogo, cantidad, activo)
VALUES (119, 3, 40, TRUE);

-- Semana 4: 29 Oct - 04 Nov
INSERT INTO documentos (id, tipo_movimiento, numero_documento, fecha, id_usuario, observacion, activo)
VALUES (120, 'INGRESO', 'ING-TEST-S4', '2025-11-01', 1, 'Ingreso semana 4', TRUE);
INSERT INTO documento_detalles (id_documento, id_producto_catalogo, cantidad, activo)
VALUES (120, 4, 120, TRUE);
INSERT INTO documentos (id, tipo_movimiento, numero_documento, fecha, id_usuario, observacion, activo)
VALUES (121, 'SALIDA', 'SAL-TEST-S4', '2025-11-03', 1, 'Salida semana 4', TRUE);
INSERT INTO documento_detalles (id_documento, id_producto_catalogo, cantidad, activo)
VALUES (121, 4, 45, TRUE);

-- Semana 5: 05-11 Nov
INSERT INTO documentos (id, tipo_movimiento, numero_documento, fecha, id_usuario, observacion, activo)
VALUES (122, 'INGRESO', 'ING-TEST-S5', '2025-11-07', 1, 'Ingreso semana 5', TRUE);
INSERT INTO documento_detalles (id_documento, id_producto_catalogo, cantidad, activo)
VALUES (122, 1, 85, TRUE);
INSERT INTO documentos (id, tipo_movimiento, numero_documento, fecha, id_usuario, observacion, activo)
VALUES (123, 'SALIDA', 'SAL-TEST-S5', '2025-11-09', 1, 'Salida semana 5', TRUE);
INSERT INTO documento_detalles (id_documento, id_producto_catalogo, cantidad, activo)
VALUES (123, 1, 32, TRUE);

-- Semana 6: 12-18 Nov
INSERT INTO documentos (id, tipo_movimiento, numero_documento, fecha, id_usuario, observacion, activo)
VALUES (124, 'INGRESO', 'ING-TEST-S6', '2025-11-14', 1, 'Ingreso semana 6', TRUE);
INSERT INTO documento_detalles (id_documento, id_producto_catalogo, cantidad, activo)
VALUES (124, 2, 95, TRUE);
INSERT INTO documentos (id, tipo_movimiento, numero_documento, fecha, id_usuario, observacion, activo)
VALUES (125, 'SALIDA', 'SAL-TEST-S6', '2025-11-16', 1, 'Salida semana 6', TRUE);
INSERT INTO documento_detalles (id_documento, id_producto_catalogo, cantidad, activo)
VALUES (125, 2, 38, TRUE);

-- Semana 7: 19-25 Nov
INSERT INTO documentos (id, tipo_movimiento, numero_documento, fecha, id_usuario, observacion, activo)
VALUES (126, 'INGRESO', 'ING-TEST-S7', '2025-11-21', 1, 'Ingreso semana 7', TRUE);
INSERT INTO documento_detalles (id_documento, id_producto_catalogo, cantidad, activo)
VALUES (126, 3, 110, TRUE);
INSERT INTO documentos (id, tipo_movimiento, numero_documento, fecha, id_usuario, observacion, activo)
VALUES (127, 'SALIDA', 'SAL-TEST-S7', '2025-11-23', 1, 'Salida semana 7', TRUE);
INSERT INTO documento_detalles (id_documento, id_producto_catalogo, cantidad, activo)
VALUES (127, 3, 42, TRUE);

-- ============================================
-- ÚLTIMOS 7 MESES (Mayo a Noviembre 2025)
-- ============================================

-- Mayo 2025
INSERT INTO documentos (id, tipo_movimiento, numero_documento, fecha, id_usuario, observacion, activo)
VALUES (128, 'INGRESO', 'ING-TEST-M1', '2025-05-15', 1, 'Ingreso Mayo', TRUE);
INSERT INTO documento_detalles (id_documento, id_producto_catalogo, cantidad, activo)
VALUES (128, 1, 200, TRUE), (128, 2, 150, TRUE);
INSERT INTO documentos (id, tipo_movimiento, numero_documento, fecha, id_usuario, observacion, activo)
VALUES (129, 'SALIDA', 'SAL-TEST-M1', '2025-05-20', 1, 'Salida Mayo', TRUE);
INSERT INTO documento_detalles (id_documento, id_producto_catalogo, cantidad, activo)
VALUES (129, 1, 80, TRUE), (129, 2, 60, TRUE);

-- Junio 2025
INSERT INTO documentos (id, tipo_movimiento, numero_documento, fecha, id_usuario, observacion, activo)
VALUES (130, 'INGRESO', 'ING-TEST-M2', '2025-06-15', 1, 'Ingreso Junio', TRUE);
INSERT INTO documento_detalles (id_documento, id_producto_catalogo, cantidad, activo)
VALUES (130, 3, 180, TRUE);
INSERT INTO documentos (id, tipo_movimiento, numero_documento, fecha, id_usuario, observacion, activo)
VALUES (131, 'SALIDA', 'SAL-TEST-M2', '2025-06-20', 1, 'Salida Junio', TRUE);
INSERT INTO documento_detalles (id_documento, id_producto_catalogo, cantidad, activo)
VALUES (131, 3, 70, TRUE);

-- Julio 2025
INSERT INTO documentos (id, tipo_movimiento, numero_documento, fecha, id_usuario, observacion, activo)
VALUES (132, 'INGRESO', 'ING-TEST-M3', '2025-07-15', 1, 'Ingreso Julio', TRUE);
INSERT INTO documento_detalles (id_documento, id_producto_catalogo, cantidad, activo)
VALUES (132, 4, 220, TRUE);
INSERT INTO documentos (id, tipo_movimiento, numero_documento, fecha, id_usuario, observacion, activo)
VALUES (133, 'SALIDA', 'SAL-TEST-M3', '2025-07-20', 1, 'Salida Julio', TRUE);
INSERT INTO documento_detalles (id_documento, id_producto_catalogo, cantidad, activo)
VALUES (133, 4, 90, TRUE);

-- Agosto 2025
INSERT INTO documentos (id, tipo_movimiento, numero_documento, fecha, id_usuario, observacion, activo)
VALUES (134, 'INGRESO', 'ING-TEST-M4', '2025-08-15', 1, 'Ingreso Agosto', TRUE);
INSERT INTO documento_detalles (id_documento, id_producto_catalogo, cantidad, activo)
VALUES (134, 1, 190, TRUE);
INSERT INTO documentos (id, tipo_movimiento, numero_documento, fecha, id_usuario, observacion, activo)
VALUES (135, 'SALIDA', 'SAL-TEST-M4', '2025-08-20', 1, 'Salida Agosto', TRUE);
INSERT INTO documento_detalles (id_documento, id_producto_catalogo, cantidad, activo)
VALUES (135, 1, 75, TRUE);

-- Septiembre 2025
INSERT INTO documentos (id, tipo_movimiento, numero_documento, fecha, id_usuario, observacion, activo)
VALUES (136, 'INGRESO', 'ING-TEST-M5', '2025-09-15', 1, 'Ingreso Septiembre', TRUE);
INSERT INTO documento_detalles (id_documento, id_producto_catalogo, cantidad, activo)
VALUES (136, 2, 210, TRUE);
INSERT INTO documentos (id, tipo_movimiento, numero_documento, fecha, id_usuario, observacion, activo)
VALUES (137, 'SALIDA', 'SAL-TEST-M5', '2025-09-20', 1, 'Salida Septiembre', TRUE);
INSERT INTO documento_detalles (id_documento, id_producto_catalogo, cantidad, activo)
VALUES (137, 2, 85, TRUE);

-- Octubre 2025
INSERT INTO documentos (id, tipo_movimiento, numero_documento, fecha, id_usuario, observacion, activo)
VALUES (138, 'INGRESO', 'ING-TEST-M6', '2025-10-15', 1, 'Ingreso Octubre', TRUE);
INSERT INTO documento_detalles (id_documento, id_producto_catalogo, cantidad, activo)
VALUES (138, 3, 230, TRUE);
INSERT INTO documentos (id, tipo_movimiento, numero_documento, fecha, id_usuario, observacion, activo)
VALUES (139, 'SALIDA', 'SAL-TEST-M6', '2025-10-20', 1, 'Salida Octubre', TRUE);
INSERT INTO documento_detalles (id_documento, id_producto_catalogo, cantidad, activo)
VALUES (139, 3, 95, TRUE);

-- Noviembre 2025
INSERT INTO documentos (id, tipo_movimiento, numero_documento, fecha, id_usuario, observacion, activo)
VALUES (140, 'INGRESO', 'ING-TEST-M7', '2025-11-15', 1, 'Ingreso Noviembre', TRUE);
INSERT INTO documento_detalles (id_documento, id_producto_catalogo, cantidad, activo)
VALUES (140, 4, 250, TRUE);
INSERT INTO documentos (id, tipo_movimiento, numero_documento, fecha, id_usuario, observacion, activo)
VALUES (141, 'SALIDA', 'SAL-TEST-M7', '2025-11-20', 1, 'Salida Noviembre', TRUE);
INSERT INTO documento_detalles (id_documento, id_producto_catalogo, cantidad, activo)
VALUES (141, 4, 100, TRUE);

-- ============================================
-- RESUMEN DE PRUEBAS
-- ============================================
-- 7 DÍAS: Verás 7 puntos con datos diarios del 23 al 29 de noviembre
-- 7 SEMANAS: Verás 7 puntos con datos semanales de octubre a noviembre
-- 7 MESES: Verás 7 puntos con datos mensuales de mayo a noviembre
-- TOP 5: El producto 4 debería aparecer primero por tener más salidas