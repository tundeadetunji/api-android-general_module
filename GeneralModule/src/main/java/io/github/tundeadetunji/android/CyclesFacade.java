package io.github.tundeadetunji.android;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.time.Month;
import java.util.List;

import io.github.tundeadetunji.android.cycles.library.CalendarLite;
import io.github.tundeadetunji.android.cycles.library.DetailViewSource;
import io.github.tundeadetunji.android.cycles.library.LanguageViewSource;
import io.github.tundeadetunji.android.cycles.library.ProfileViewSource;
import io.github.tundeadetunji.android.cycles.model.Entity;
import io.github.tundeadetunji.android.cycles.model.domain.Cycle;
import io.github.tundeadetunji.android.cycles.model.domain.Language;
import io.github.tundeadetunji.android.cycles.model.domain.Period;

public class CyclesFacade {
    private static CyclesFacade instance;

    public static CyclesFacade getInstance() {
        if (instance == null) instance = new CyclesFacade();
        return instance;
    }

    private CyclesFacade() {
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public Entity createEntity(ProfileViewSource resource) {
        return new Entity(Integer.parseInt(resource.getDayDropDown().getText().toString()), CalendarLite.getInstance().getIntFromMonth(resource.getMonthDropDown().getText().toString()), !resource.getProfileDropDown().getText().toString().isEmpty() ? resource.getProfileDropDown().getText().toString() : "Entity");
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public Entity createEntity(int month, int day, String name) {
        return new Entity(day, month, name);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public DetailResource toLanguage(ProfileViewSource profileResource, Language language) {

        Entity mEntity = createEntity(profileResource);

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

    public boolean personalCyclesArePresent(DetailViewSource resource) {
        return !resource.getPersonalTextView().getText().toString().isEmpty();
    }

    public String createFilename(ProfileViewSource resource) {
        return (resource.getProfileDropDown().getText().toString().isEmpty() ? "Entity" : resource.getProfileDropDown().getText().toString()) + ".txt";
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public boolean userInputIsValid(ProfileViewSource resource) {
        if (resource.getDayDropDown().getText().toString().isEmpty() || resource.getMonthDropDown().getText().toString().isEmpty())
            return false;

        if (Integer.parseInt(resource.getDayDropDown().getText().toString()) > 28 && resource.getMonthDropDown().getText().toString().equalsIgnoreCase(Month.FEBRUARY.name()))
            return false;

        if (Integer.parseInt(resource.getDayDropDown().getText().toString()) > 30 &&
                (!resource.getMonthDropDown().getText().toString().equalsIgnoreCase(Month.SEPTEMBER.name()) &&
                        !resource.getMonthDropDown().getText().toString().equalsIgnoreCase(Month.APRIL.name()) &&
                        !resource.getMonthDropDown().getText().toString().equalsIgnoreCase(Month.JUNE.name()) &&
                        !resource.getMonthDropDown().getText().toString().equalsIgnoreCase(Month.NOVEMBER.name())))
            return false;

        return true;
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    public String scroll(DetailViewSource detailsResource, ProfileViewSource profilesResource, LanguageViewSource languageResource) {
        return title(detailsResource, languageResource) + "\n" + headline(detailsResource) + BREAK + dates(createEntity(profilesResource).getPERIOD_LISTING()) + BREAK + personal(detailsResource) + BREAK + health(detailsResource) + BREAK + business(detailsResource) + BREAK + soul(detailsResource);
    }

    private final String BREAK = "\n\n";

    private String headline(DetailViewSource resource) {
        return resource.getHeadlineTextView().getText().toString();
    }

    private String title(DetailViewSource detailsResource, LanguageViewSource languageResource) {
        Language language = languageResource.getLanguageDropDown().getText().toString().isEmpty() ? Language.English : Language.valueOf(languageResource.getLanguageDropDown().getText().toString());

        if (language == Language.English) {
            return "Cycles Information for " + headline(detailsResource).split("belongs")[0].trim();
        } else if (language == Language.Bulgarian) {
            return "Цикли Информация за " + headline(detailsResource).split("принадлежи")[0].trim();
        }

        return "Awọn iyika Alaye fun " + headline(detailsResource).split("je")[0].trim();
    }

    private String dates(List<String> listing) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < listing.size(); i++) {
            result.append(Period.toCanonicalString(i + 1)).append(": ").append(listing.get(i)).append("\n");
        }

        return result.toString();
    }


    private String personal(DetailViewSource resource) {
        return Cycle.Personal.name() + BREAK + resource.getPersonalTextView().getText().toString();
    }

    private String health(DetailViewSource resource) {
        return Cycle.Health.name() + BREAK + resource.getHealthTextView().getText().toString();
    }

    private String business(DetailViewSource resource) {
        return Cycle.Business.name() + BREAK + resource.getBusinessTextView().getText().toString();
    }

    private String soul(DetailViewSource resource) {
        return Cycle.Soul.name() + BREAK + resource.getSoulTextView().getText().toString();
    }


    public static class DetailResource {
        private final String headline;
        private final String soul;
        private final String personal;

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

        private String business;
        private String health;


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

}
