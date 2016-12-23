/**
 * This class is generated by jOOQ
 */
package main.java.nl.tue.ieis.is.basex.entities.jooq;


import javax.annotation.Generated;

import main.java.nl.tue.ieis.is.basex.entities.jooq.tables.Actor;
import main.java.nl.tue.ieis.is.basex.entities.jooq.tables.BmActor;
import main.java.nl.tue.ieis.is.basex.entities.jooq.tables.BusinessModel;
import main.java.nl.tue.ieis.is.basex.entities.jooq.tables.Company;
import main.java.nl.tue.ieis.is.basex.entities.jooq.tables.CostBenefit;
import main.java.nl.tue.ieis.is.basex.entities.jooq.tables.CostBenefitActor;
import main.java.nl.tue.ieis.is.basex.entities.jooq.tables.CpActivity;
import main.java.nl.tue.ieis.is.basex.entities.jooq.tables.LocationGui;
import main.java.nl.tue.ieis.is.basex.entities.jooq.tables.Strategy;
import main.java.nl.tue.ieis.is.basex.entities.jooq.tables.User;
import main.java.nl.tue.ieis.is.basex.entities.jooq.tables.UserHasStrategy;


/**
 * Convenience access to all tables in basex_database
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.8.6"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Tables {

    /**
     * The table <code>basex_database.actor</code>.
     */
    public static final Actor ACTOR = main.java.nl.tue.ieis.is.basex.entities.jooq.tables.Actor.ACTOR;

    /**
     * The table <code>basex_database.bm_actor</code>.
     */
    public static final BmActor BM_ACTOR = main.java.nl.tue.ieis.is.basex.entities.jooq.tables.BmActor.BM_ACTOR;

    /**
     * The table <code>basex_database.business_model</code>.
     */
    public static final BusinessModel BUSINESS_MODEL = main.java.nl.tue.ieis.is.basex.entities.jooq.tables.BusinessModel.BUSINESS_MODEL;

    /**
     * The table <code>basex_database.company</code>.
     */
    public static final Company COMPANY = main.java.nl.tue.ieis.is.basex.entities.jooq.tables.Company.COMPANY;

    /**
     * The table <code>basex_database.cost_benefit</code>.
     */
    public static final CostBenefit COST_BENEFIT = main.java.nl.tue.ieis.is.basex.entities.jooq.tables.CostBenefit.COST_BENEFIT;

    /**
     * The table <code>basex_database.cost_benefit_actor</code>.
     */
    public static final CostBenefitActor COST_BENEFIT_ACTOR = main.java.nl.tue.ieis.is.basex.entities.jooq.tables.CostBenefitActor.COST_BENEFIT_ACTOR;

    /**
     * The table <code>basex_database.cp_activity</code>.
     */
    public static final CpActivity CP_ACTIVITY = main.java.nl.tue.ieis.is.basex.entities.jooq.tables.CpActivity.CP_ACTIVITY;

    /**
     * The table <code>basex_database.location_gui</code>.
     */
    public static final LocationGui LOCATION_GUI = main.java.nl.tue.ieis.is.basex.entities.jooq.tables.LocationGui.LOCATION_GUI;

    /**
     * The table <code>basex_database.strategy</code>.
     */
    public static final Strategy STRATEGY = main.java.nl.tue.ieis.is.basex.entities.jooq.tables.Strategy.STRATEGY;

    /**
     * The table <code>basex_database.user</code>.
     */
    public static final User USER = main.java.nl.tue.ieis.is.basex.entities.jooq.tables.User.USER;

    /**
     * The table <code>basex_database.user_has_strategy</code>.
     */
    public static final UserHasStrategy USER_HAS_STRATEGY = main.java.nl.tue.ieis.is.basex.entities.jooq.tables.UserHasStrategy.USER_HAS_STRATEGY;
}