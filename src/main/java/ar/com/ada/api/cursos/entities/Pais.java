package ar.com.ada.api.cursos.entities;

public class Pais {

    // creamos el objeto pais para utilizar su enumerado
    
    public enum PaisEnum {
        ARGENTINA(32), 
        VENEZUELA(840),
        ESTADOS_UNIDOS(862);

        private final Integer value;

        // NOTE: Enum constructor tiene que estar en privado
        private PaisEnum(Integer value) {
            this.value = value;
        }

        public Integer getValue() {
            return value;
        }

        public static PaisEnum parse(Integer id) {
            PaisEnum status = null; // Default
            for (PaisEnum item : PaisEnum.values()) {
                if (item.getValue().equals(id)) {
                    status = item;
                    break;
                }
            }
            return status;
        }
    }

    public enum TipoDocEnum {
        DNI(1), 
        PASAPORTE(2);

        private final Integer value;

        // NOTE: Enum constructor tiene que estar en privado
        private TipoDocEnum(Integer value) {
            this.value = value;
        }

        public Integer getValue() {
            return value;
        }

        public static TipoDocEnum parse(Integer id) {
            TipoDocEnum status = null; // Default
            for (TipoDocEnum item : TipoDocEnum.values()) {
                if (item.getValue().equals(id)) {
                    status = item;
                    break;
                }
            }
            return status;
        }
    }

}