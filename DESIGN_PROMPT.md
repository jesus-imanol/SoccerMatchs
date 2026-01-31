# Prompt para Diseño UI de App de Fútbol

## Prompt para Google Gemini / IA de Diseño

```
Necesito un diseño moderno y profesional para una pantalla de partidos de fútbol en Android con Jetpack Compose Material 3.

CONTEXTO:
- App de fútbol que muestra partidos del día
- Usa la API de football-data.org
- Tecnología: Kotlin, Jetpack Compose, Material 3
- Usuarios objetivo: Fanáticos del fútbol que quieren ver partidos en tiempo real

ELEMENTOS ACTUALES EN LA PANTALLA:
1. Top Bar con título "Matches" y botón de calendario
2. Fila de botones: "Today", "Tomorrow", "Custom" (para seleccionar fecha)
3. Chips horizontales con scroll para filtrar por liga:
   - All, Premier League, La Liga, Bundesliga, Serie A, Ligue 1, Champions League, Europa League
4. Lista de tarjetas de partidos que muestran:
   - Nombre de la competición
   - Escudos de equipos (local y visitante)
   - Nombres de equipos
   - Hora del partido (HH:mm) o marcador si ya terminó (2 - 1)
   - Estado del partido (SCHEDULED, IN_PLAY, FINISHED)

MEJORAS QUE QUIERO DISEÑAR:

1. **Indicador Visual de Estado del Partido**
   - Partidos EN VIVO: badge rojo animado con texto "LIVE" o punto pulsante
   - Partidos FINALIZADOS: badge gris con "FT" (Full Time)
   - Partidos PROGRAMADOS: mostrar hora de inicio
   - Partidos POSPUESTOS: badge amarillo con "PP"

2. **Jerarquía Visual Mejorada**
   - Los partidos en vivo deberían destacarse más (quizás un borde brillante o color de fondo diferente)
   - Usar colores del Material 3 theme apropiadamente

3. **Información Adicional en las Cards**
   - Minuto del partido si está en vivo (ej: "67'")
   - Indicador de tarjetas rojas/amarillas si las hay
   - Fecha/hora más prominente

4. **Animaciones Sutiles**
   - El badge "LIVE" debería tener una animación sutil (pulso o brillo)
   - Transiciones suaves al cambiar de filtro

5. **Estados Vacíos**
   - Diseño para cuando no hay partidos
   - Diseño para estado de carga
   - Diseño para error de conexión

RESTRICCIONES TÉCNICAS:
- Debe ser implementable en Jetpack Compose Material 3
- Colores deben seguir el sistema de Material Design 3
- Debe verse bien en modo claro y oscuro
- Priorizar legibilidad y rendimiento

ESTILO VISUAL DESEADO:
- Moderno y limpio (estilo Material You)
- Espacios generosos entre elementos
- Tipografía clara y legible
- Iconografía simple y reconocible
- Colores vibrantes pero no estridentes

POR FAVOR GENERA:
1. Descripción detallada del diseño propuesto
2. Paleta de colores específica (en formato hex)
3. Especificaciones de spacing, padding y elevación
4. Mockup visual o sketch de la interfaz
5. Detalles de animaciones y transiciones
6. Código de ejemplo en Jetpack Compose si es posible

EJEMPLO DE REFERENCIA:
Similar a apps como: OneFootball, ESPN, Sofascore - pero con tu toque único y moderno.
```

---

## Variante Corta (Si la IA tiene límite de tokens)

```
Diseña una pantalla moderna de partidos de fútbol para Android (Jetpack Compose Material 3):

ELEMENTOS:
- Top bar con calendario
- Botones de fecha: Today/Tomorrow/Custom
- Chips de filtro por liga (horizontal scroll)
- Cards de partidos con: escudos, equipos, competición, hora/marcador

MEJORAS NECESARIAS:
1. Badge "LIVE" animado para partidos en vivo (rojo pulsante)
2. Destacar visualmente partidos en vivo vs finalizados
3. Badge "FT" para finalizados, hora para programados
4. Mejor jerarquía visual y espaciado
5. Estados: vacío, carga, error

ESTILO: Material You, moderno, limpio, modo claro/oscuro

ENTREGA: Descripción detallada + paleta de colores + especificaciones + mockup
```

---

## Preguntas de Seguimiento para Refinar el Diseño

Una vez que la IA te dé el diseño inicial, puedes hacer preguntas como:

1. "¿Cómo implementarías la animación del badge LIVE en Jetpack Compose?"
2. "¿Qué color específico recomiendas para los partidos en vivo que contraste bien?"
3. "¿Puedes darme el código Compose para esta card de partido?"
4. "¿Cómo haríamos la transición entre filtros más suave?"
5. "¿Qué iconos de Material Icons recomiendas para cada estado?"

---

## Tips para Mejores Resultados

1. **Sé específico** sobre la tecnología (Jetpack Compose Material 3)
2. **Incluye contexto** de tu audiencia y caso de uso
3. **Proporciona referencias** de apps similares que te gusten
4. **Pide variaciones** si no te convence el primer diseño
5. **Solicita código** además del diseño visual para implementación más rápida

---

## Información Técnica Actual de tu App

**Componentes actuales:**
- `MatchCard.kt` - Tarjeta de partido
- `SoccerTeamScreen.kt` - Pantalla principal
- Material 3 Theme configurado
- Coil para imágenes
- LocalDate para fechas

**API disponible:**
- `match.status`: "SCHEDULED", "IN_PLAY", "FINISHED", "POSTPONED", etc.
- `match.homeScore`, `match.awayScore`
- `match.utcDate`
- `match.competitionName`

Esta información ayudará a la IA a dar sugerencias más precisas.

