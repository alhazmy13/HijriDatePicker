package net.alhazmy13.hijridatepicker;

/**
 * Created by Alhazmy13 on 2/3/16.
 */
public interface EnumConfig {
    enum Mode{
        Hijri(1),
        Gregorian(2);
        private int mode;
        Mode(int mode) {
            this.mode = mode;
        }
        public int getModeValue() {
            return mode;
        }

    }

    enum Language{
        Arabic(1),
        English(2);
        private int language;
        Language(int language) {
            this.language = language;
        }
        public int getLanguageValue() {
            return language;
        }

    }
}
