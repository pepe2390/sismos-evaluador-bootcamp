#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>
#include <unistd.h> 

#define MAX_REGIONES 16
#define MAX_PROVINCIAS 8 
#define MAX_COMBINATIONS 100000 
#define COMBINATION_LEN 30      


// ESTRUCTURAS DE DATOS

typedef struct {
    int luz;
    int agua;
    int comida;
    int medicos;
    int comunicaciones;
} RecursosData;

typedef struct {
    const char* nombre_provincia;
    int Id; // <-- Este es el ID que usaremos
} ProvinciaData;

typedef struct {
    const char* nombre_region;
    ProvinciaData provincias[MAX_PROVINCIAS];
    int num_provincias;
} RegionData;


// DATOS SIMULADOS DE REGIONES Y PROVINCIAS
RegionData regiones_chile[MAX_REGIONES] = {
    {"Arica y Parinacota", {
        {"Arica", 0}, 
        {"Parinacota", 1}
    }, 2},
    {"Tarapaca", {
        {"Iquique", 2}, 
        {"Tamarugal", 3}
    }, 2},
    {"Antofagasta", {
        {"Antofagasta", 4}, 
        {"El Loa", 5}, 
        {"Tocopilla", 6}
    }, 3},
    {"Atacama", {
        {"Copiapo", 7}, 
        {"Chañaral", 8}, 
        {"Huasco", 9}
    }, 3},
    {"Coquimbo", {
        {"Elqui", 10}, 
        {"Limari", 12}, // Ojo: IDs no son consecutivos
        {"Choapa", 11}
    }, 3},
    {"Valparaiso", {
        {"Valparaiso", 13}, 
        {"San Antonio", 19}, 
        {"San Felipe de Aconcagua", 20}, 
        {"Los Andes", 15}, 
        {"Petorca", 17}, 
        {"Quillota", 18}, 
        {"Marga Marga", 16}, 
        {"Isla de Pascua", 14}
    }, 8},
    {"Metropolitana", {
        {"Santiago", 21}, 
        {"Chacabuco", 22}, 
        {"Cordillera", 23}, 
        {"Maipo", 24}, 
        {"Melipilla", 25}, 
        {"Talagante", 26}
    }, 6},
    {"O'Higgins", {
        {"Cachapoal", 27}, 
        {"Colchagua", 28}, 
        {"Cardenal Caro", 29}
    }, 3},
    {"Maule", {
        {"Talca", 30}, 
        {"Curico", 32}, 
        {"Linares", 33}, 
        {"Cauquenes", 31}
    }, 4},
    {"Nuble", {
        {"Diguillin", 34}, 
        {"Itata", 35}, 
        {"Punilla", 36}
    }, 3},
    {"Biobio", {
        {"Concepcion", 37}, 
        {"Arauco", 38}, 
        {"Biobio", 39}
    }, 3},
    {"La Araucania", {
        {"Cautin", 40}, 
        {"Malleco", 41}
    }, 2},
    {"Los Rios", {
        {"Valdivia", 42}, 
        {"Ranco", 43}
    }, 2},
    {"Los Lagos", {
        {"Llanquihue", 44}, 
        {"Osorno", 46}, 
        {"Chiloe", 45}, 
        {"Palena", 47}
    }, 4},
    {"Aysen", {
        {"Coyhaique", 48}, 
        {"Aysen", 49}, 
        {"General Carrera", 51}, 
        {"Capitan Prat", 50}
    }, 4},
    {"Magallanes", {
        {"Magallanes", 52}, 
        {"Tierra del Fuego", 54}, 
        {"Ultima Esperanza", 55}, 
        {"Antartica Chilena", 53}
    }, 4}
};

char used_combinations[MAX_COMBINATIONS][COMBINATION_LEN];
int combination_count = 0;

// FUNCIONES AUXILIARES

void limpiarPantalla() {
#ifdef _WIN32
    system("cls");
#else
    system("clear");
#endif
}

float rand_float(float min, float max) {
    return min + ((float)rand() / RAND_MAX) * (max - min);
}

void generador_probabilidad(float* magnitud) {
    int probabilidad = rand() % 10;
    
    if (probabilidad < 9)
        *magnitud = rand_float(1.0, 6.0);
    else
        *magnitud = rand_float(6.0, 9.5); 
}

void obtener_fecha_a_partir_de_indice(int dia_total_indice, char *buffer) {
    struct tm inicio = {0};
    inicio.tm_year = 2024 - 1900; 
    inicio.tm_mon = 0;          
    inicio.tm_mday = 1;         

    time_t tiempo_unix = mktime(&inicio);
    tiempo_unix += (long)dia_total_indice * 24 * 60 * 60;
    struct tm *fecha_actual = localtime(&tiempo_unix);

    
    strftime(buffer, 11, "%Y-%m-%d", fecha_actual);
}

int is_combination_unique(const char *fecha_provincia) {
    if (combination_count >= MAX_COMBINATIONS) {
        return 0; 
    }
    for (int i = 0; i < combination_count; i++) {
        if (strcmp(used_combinations[i], fecha_provincia) == 0) {
            return 0; // No es única
        }
    }
    strcpy(used_combinations[combination_count], fecha_provincia);
    combination_count++;
    return 1;
}

// FUNCIÓN PRINCIPAL DE GENERACIÓN DE DATOS

void generar_datos() {
    FILE *archivo = fopen("datos.txt", "w");
    int ANIOS_A_SIMULAR = 0; 
    int SISMOS_POR_ANIO = 0; 
    int SISMOS_TOTALES = 0;
    
    if (!archivo) {
        printf("Error al crear el archivo.\n");
        return;
    }
    
    printf("Ingresa la cantidad de ANIOS a simular (a partir de 2024):\n");
    while (ANIOS_A_SIMULAR < 1){
        if(scanf("%d", &ANIOS_A_SIMULAR) != 1 || ANIOS_A_SIMULAR < 1){
            printf("Valor invalido. Intenta de nuevo: ");
            while (getchar() != '\n'); 
        } else {
            break;
        }
    }

    printf("Ingresa la cantidad de SISMOS totales por ANIO a generar:\n");
    while (SISMOS_POR_ANIO < 1){
        if(scanf("%d", &SISMOS_POR_ANIO) != 1 || SISMOS_POR_ANIO < 1){
            printf("Valor invalido. Intenta de nuevo: ");
            while (getchar() != '\n'); 
        } else {
            break;
        }
    }
    
    SISMOS_TOTALES = ANIOS_A_SIMULAR * SISMOS_POR_ANIO;
    int TOTAL_DIAS_SIMULACION = ANIOS_A_SIMULAR * 366; 

    srand(time(NULL));
    combination_count = 0; 

   
    fprintf(archivo, "Fecha,Region,Provincia,Magnitud,IndiceProvincia\n");

    int sismos_generados = 0;
    int intentos_fallidos = 0;

    while (sismos_generados < SISMOS_TOTALES) {
        
        float magnitud;
        generador_probabilidad(&magnitud);

        int indice_region = rand() % MAX_REGIONES;
        RegionData region_actual = regiones_chile[indice_region];

        int indice_provincia = rand() % region_actual.num_provincias;
        ProvinciaData provincia_actual = region_actual.provincias[indice_provincia];
        
        const char *nombre_provincia = provincia_actual.nombre_provincia;
        
        // --- CAMBIO 2: Extraer el ID ---
        int id_provincia = provincia_actual.Id; 

        int dia_aleatorio = rand() % TOTAL_DIAS_SIMULACION; 
        char fecha[11];
        obtener_fecha_a_partir_de_indice(dia_aleatorio, fecha);

        char combinacion[COMBINATION_LEN];
        snprintf(combinacion, COMBINATION_LEN, "%s_%s", fecha, nombre_provincia);

        if (!is_combination_unique(combinacion)) {
            intentos_fallidos++;
            if (intentos_fallidos > SISMOS_TOTALES * 2) {
                printf("\nAlerta: Se detuvo la generacion por excesivos intentos fallidos de unicidad. Se generaron %d de %d sismos.\n", sismos_generados, SISMOS_TOTALES);
                break; 
            }
            continue; 
        }
        
        
        fprintf(archivo, "%s,\"%s\",\"%s\",%.2f,%d\n", 
                fecha,
                region_actual.nombre_region,
                nombre_provincia,
                magnitud,
                id_provincia); // <-- ID añadido
                
        sismos_generados++;
        intentos_fallidos = 0; 
    }

    fclose(archivo);
    printf("Datos generados correctamente en 'datos.txt' (%d sismos simulados en %d años) con formato CSV.\n", sismos_generados, ANIOS_A_SIMULAR);
}


// FUNCIÓN PRINCIPAL MAIN

int main() {
    int opcion;
    do {
        printf("\nMenu de Sismos en Chile\n");
        printf("1. Generar datos simulados\n");
        printf("0. Salir\n");
        printf("Seleccione una opcion: ");

        if (scanf("%d", &opcion) != 1) {
            printf("Entrada invalida. Reiniciando menu...\n");
            while (getchar() != '\n'); 
            opcion = -1;
            continue;
        }

        switch (opcion) {
            case 1:
                limpiarPantalla();
                generar_datos();
                break;
            case 0:
                limpiarPantalla();
                printf("Saliendo del programa...\n");
                break;
            default:
                limpiarPantalla();
                printf("Opcion invalida. Intente nuevamente.\n");
                break;
        }
    } while (opcion != 0);

    return 0;
}