package io.github.tundeadetunji.android;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.time.Month;
import java.util.List;

import io.github.tundeadetunji.android.cycles.model.Entity;
import io.github.tundeadetunji.android.cycles.model.domain.Cycle;
import io.github.tundeadetunji.android.cycles.model.domain.Language;
import io.github.tundeadetunji.android.cycles.model.domain.Period;

/**
 * Same as CyclesFacade, but not making any assumption on what type any View is.
 */
public class CyclesFacadeLite {
    private static CyclesFacadeLite instance;

    public static CyclesFacadeLite getInstance() {
        if (instance == null) instance = new CyclesFacadeLite();
        return instance;
    }

    private CyclesFacadeLite() {
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public Entity createEntity(int month, int day, String name) {
        if (!userInputIsValid(Month.of(month), day, name)) throw new IllegalArgumentException("Some fields are invalid.");
        return new Entity(day, month, name);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public DetailResource toLanguage(Entity mEntity, Language language) {

        switch (language) {
            case English:
                return loadEnglish(mEntity);
            case Yoruba:
                return loadYoruba(mEntity);
            case Bulgarian:
                return loadBulgarian(mEntity);
        }

        throw new RuntimeException("Language not understood: expected English, Yoruba or Bulgarian.");
    }

    private DetailResource loadEnglish(Entity entity) {
        return DetailResource.create(
                entity.getHEADLINE(),
                entity.getSOUL(),
                entity.getPERSONAL(),
                entity.getBUSINESS(),
                entity.getHEALTH()
        );
    }

    private DetailResource loadYoruba(Entity entity) {
        return DetailResource.create(
                entity.getHEADLINE_IN_YORUBA(),
                entity.getSOUL_IN_YORUBA(),
                entity.getPERSONAL_IN_YORUBA(),
                entity.getBUSINESS_IN_YORUBA(),
                entity.getHEALTH_IN_YORUBA()
        );
    }

    private DetailResource loadBulgarian(Entity entity) {
        return DetailResource.create(
                entity.getHEADLINE_IN_BULGARIAN(),
                entity.getSOUL_IN_BULGARIAN(),
                entity.getPERSONAL_IN_BULGARIAN(),
                entity.getBUSINESS_IN_BULGARIAN(),
                entity.getHEALTH_IN_BULGARIAN()
        );
    }

    public String createFilename(String name) {
        return (name + ".txt");
    }

    public String createTitleForSchedule(String name) {
        //Todo reflect if it's business or person, check wherever else applicable
        return name;
    }

    public String createDescriptionForSchedule(String name) {
        return "Yearly cycle for " + createTitleForSchedule(name);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public boolean userInputIsValid(Month month, int day, String name) {
        if (name.isEmpty()) return false;

        if (day > 28 && month.equals(Month.FEBRUARY)) return false;

        if (day > 30 &&
                (!month.equals(Month.SEPTEMBER) &&
                        !month.equals(Month.APRIL) &&
                        !month.equals(Month.JUNE) &&
                        !month.equals(Month.NOVEMBER)))
            return false;

        return true;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public String scroll(Entity entity, Language language) {
        return title(entity.getHEADLINE(), language) + "\n" + entity.getHEADLINE() + BREAK + dates(entity.getPERIOD_LISTING()) + BREAK + personal(entity.getPERSONAL()) + BREAK + health(entity.getHEALTH()) + BREAK + business(entity.getBUSINESS()) + BREAK + soul(entity.getSOUL());
    }

    private final String BREAK = "\n\n";

    private String title(String headline, Language language) {

        if (language == Language.English) {
            return "Cycles Information for " + headline.split("belongs")[0].trim();
        } else if (language == Language.Bulgarian) {
            return "Цикли Информация за " + headline.split("принадлежи")[0].trim();
        }

        return "Awọn iyika Alaye fun " + headline.split("je")[0].trim();
    }

    private String dates(List<String> listing) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < listing.size(); i++) {
            result.append(Period.toCanonicalString(i + 1)).append(": ").append(listing.get(i)).append("\n");
        }

        return result.toString();
    }


    private String personal(String personal) {
        return Cycle.Personal.name() + BREAK + personal;
    }

    private String health(String health) {
        return Cycle.Health.name() + BREAK + health;
    }

    private String business(String business) {
        return Cycle.Business.name() + BREAK + business;
    }

    private String soul(String soul) {
        return Cycle.Soul.name() + BREAK + soul;
    }


    public static class DetailResource {
        private final String headline;
        private final String soul;
        private final String personal;
        private final String business;

        public String getHeadline() {
            return headline;
        }

        public String getSoul() {
            return soul;
        }

        public String getPersonal() {
            return personal;
        }

        public String getBusiness() {
            return business;
        }

        public String getHealth() {
            return health;
        }

        private final String health;

        private DetailResource(String headline, String soul, String personal, String business, String health) {
            this.headline = headline;
            this.soul = soul;
            this.personal = personal;
            this.business = business;
            this.health = health;
        }
        public static DetailResource create(String headline, String soul, String personal, String business, String health) {
            return new DetailResource(headline, soul, personal, business, health);
        }
    }


    public static class DateResource {
        public int getDay() {
            return day;
        }

        public int getMonth() {
            return month;
        }

        private int day;
        private int month;

        private DateResource(int day, int month) {
            this.day = day;
            this.month = month;
        }
    }

}
