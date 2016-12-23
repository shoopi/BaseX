/**
 * This class is generated by jOOQ
 */
package main.java.nl.tue.ieis.is.basex.entities.jooq.tables;


import java.util.Arrays;
import java.util.List;

import javax.annotation.Generated;

import main.java.nl.tue.ieis.is.basex.entities.jooq.BasexDatabase;
import main.java.nl.tue.ieis.is.basex.entities.jooq.Keys;
import main.java.nl.tue.ieis.is.basex.entities.jooq.tables.records.CostBenefitActorRecord;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.UniqueKey;
import org.jooq.impl.TableImpl;


/**
 * This class is generated by jOOQ.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.8.6"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class CostBenefitActor extends TableImpl<CostBenefitActorRecord> {

    private static final long serialVersionUID = -1798287954;

    /**
     * The reference instance of <code>basex_database.cost_benefit_actor</code>
     */
    public static final CostBenefitActor COST_BENEFIT_ACTOR = new CostBenefitActor();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<CostBenefitActorRecord> getRecordType() {
        return CostBenefitActorRecord.class;
    }

    /**
     * The column <code>basex_database.cost_benefit_actor.CB_ACTOR_ID</code>.
     */
    public final TableField<CostBenefitActorRecord, String> CB_ACTOR_ID = createField("CB_ACTOR_ID", org.jooq.impl.SQLDataType.VARCHAR.length(40).nullable(false), this, "");

    /**
     * The column <code>basex_database.cost_benefit_actor.BM_ACTOR_ID</code>.
     */
    public final TableField<CostBenefitActorRecord, String> BM_ACTOR_ID = createField("BM_ACTOR_ID", org.jooq.impl.SQLDataType.VARCHAR.length(40).nullable(false), this, "");

    /**
     * The column <code>basex_database.cost_benefit_actor.CB_ID</code>.
     */
    public final TableField<CostBenefitActorRecord, String> CB_ID = createField("CB_ID", org.jooq.impl.SQLDataType.VARCHAR.length(40).nullable(false), this, "");

    /**
     * The column <code>basex_database.cost_benefit_actor.COST_OR_BENEFIT</code>.
     */
    public final TableField<CostBenefitActorRecord, String> COST_OR_BENEFIT = createField("COST_OR_BENEFIT", org.jooq.impl.SQLDataType.VARCHAR.length(1).nullable(false), this, "");

    /**
     * Create a <code>basex_database.cost_benefit_actor</code> table reference
     */
    public CostBenefitActor() {
        this("cost_benefit_actor", null);
    }

    /**
     * Create an aliased <code>basex_database.cost_benefit_actor</code> table reference
     */
    public CostBenefitActor(String alias) {
        this(alias, COST_BENEFIT_ACTOR);
    }

    private CostBenefitActor(String alias, Table<CostBenefitActorRecord> aliased) {
        this(alias, aliased, null);
    }

    private CostBenefitActor(String alias, Table<CostBenefitActorRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, "");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Schema getSchema() {
        return BasexDatabase.BASEX_DATABASE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UniqueKey<CostBenefitActorRecord> getPrimaryKey() {
        return Keys.KEY_COST_BENEFIT_ACTOR_PRIMARY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UniqueKey<CostBenefitActorRecord>> getKeys() {
        return Arrays.<UniqueKey<CostBenefitActorRecord>>asList(Keys.KEY_COST_BENEFIT_ACTOR_PRIMARY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ForeignKey<CostBenefitActorRecord, ?>> getReferences() {
        return Arrays.<ForeignKey<CostBenefitActorRecord, ?>>asList(Keys.FK_COST_BENEFIT_ACTOR_BM_ACTOR1, Keys.FK_COST_BENEFIT_ACTOR_COST_BENEFIT1);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CostBenefitActor as(String alias) {
        return new CostBenefitActor(alias, this);
    }

    /**
     * Rename this table
     */
    public CostBenefitActor rename(String name) {
        return new CostBenefitActor(name, null);
    }
}
