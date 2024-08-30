package io.github.tundeadetunji.android.cycles.model;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.List;

import io.github.tundeadetunji.android.cycles.model.domain.Period;
import io.github.tundeadetunji.android.cycles.model.value.Business;
import io.github.tundeadetunji.android.cycles.model.value.Health;
import io.github.tundeadetunji.android.cycles.model.value.Personal;
import io.github.tundeadetunji.android.cycles.model.value.Soul;

public class Entity {

    public final String HEADLINE;
    public final String HEADLINE_IN_YORUBA;
    public final String HEADLINE_IN_BULGARIAN;
    public final String PERSONAL;
    public final String PERSONAL_IN_YORUBA;
    public final String PERSONAL_IN_BULGARIAN;
    public final String HEALTH;
    public final String HEALTH_IN_YORUBA;
    public final String HEALTH_IN_BULGARIAN;
    public final String BUSINESS;
    public final String BUSINESS_IN_YORUBA;

    public String getHEADLINE() {
        return HEADLINE;
    }

    public String getHEADLINE_IN_YORUBA() {
        return HEADLINE_IN_YORUBA;
    }

    public String getHEADLINE_IN_BULGARIAN() {
        return HEADLINE_IN_BULGARIAN;
    }

    public String getPERSONAL() {
        return PERSONAL;
    }

    public String getPERSONAL_IN_YORUBA() {
        return PERSONAL_IN_YORUBA;
    }

    public String getPERSONAL_IN_BULGARIAN() {
        return PERSONAL_IN_BULGARIAN;
    }

    public String getHEALTH() {
        return HEALTH;
    }

    public String getHEALTH_IN_YORUBA() {
        return HEALTH_IN_YORUBA;
    }

    public String getHEALTH_IN_BULGARIAN() {
        return HEALTH_IN_BULGARIAN;
    }

    public String getBUSINESS() {
        return BUSINESS;
    }

    public String getBUSINESS_IN_YORUBA() {
        return BUSINESS_IN_YORUBA;
    }

    public String getBUSINESS_IN_BULGARIAN() {
        return BUSINESS_IN_BULGARIAN;
    }

    public String getSOUL() {
        return SOUL;
    }

    public String getSOUL_IN_YORUBA() {
        return SOUL_IN_YORUBA;
    }

    public String getSOUL_IN_BULGARIAN() {
        return SOUL_IN_BULGARIAN;
    }

    public List<String> getPERIOD_LISTING() {
        return PERIOD_LISTING;
    }

    public Period getPERIOD() {
        return PERIOD;
    }

    public String getNAME() {
        return NAME;
    }

    public final String BUSINESS_IN_BULGARIAN;
    public final String SOUL;
    public final String SOUL_IN_YORUBA;
    public final String SOUL_IN_BULGARIAN;
    public final List<String> PERIOD_LISTING;
    public final Period PERIOD;
    public final String NAME;

    @RequiresApi(api = Build.VERSION_CODES.O)
    public Entity(int day, int month, String name) {
        Soul soul = new Soul(day, month, name);
        Personal personal = new Personal(day, month, name);

        this.NAME = name;
        this.PERIOD_LISTING = personal.getListing();
        this.PERIOD = personal.getPeriod();


        this.HEADLINE = soul.getHeadline() + "\n\n" + personal.getHeadline();
        this.HEADLINE_IN_YORUBA = soul.getHeadlineInYoruba() + "\n\n" + personal.getHeadlineInYoruba();
        this.HEADLINE_IN_BULGARIAN = soul.getHeadlineInBulgarian() + "\n\n" + personal.getHeadlineInBulgarian();

        this.PERSONAL = personal.getDetails();
        this.PERSONAL_IN_YORUBA = personal.getDetailsInYoruba();
        this.PERSONAL_IN_BULGARIAN = personal.getDetailsInBulgarian();

        this.SOUL = soul.getDetails();
        this.SOUL_IN_YORUBA = soul.getDetailsInYoruba();
        this.SOUL_IN_BULGARIAN = soul.getDetailsInBulgarian();

        Health health = new Health();
        this.HEALTH = health.constructDetails(this.PERIOD);
        this.HEALTH_IN_YORUBA = health.constructDetailsInYoruba(this.PERIOD);
        this.HEALTH_IN_BULGARIAN = health.constructDetailsInBulgarian(this.PERIOD);

        Business business = new Business();
        this.BUSINESS = business.constructDetails(this.PERIOD);
        this.BUSINESS_IN_YORUBA = business.constructDetailsInYoruba(this.PERIOD);
        this.BUSINESS_IN_BULGARIAN = business.constructDetailsInBulgarian(this.PERIOD);
    }


}
