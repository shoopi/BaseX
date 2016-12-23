/**
 * This class is generated by jOOQ
 */
package main.java.nl.tue.ieis.is.basex.entities.jooq.tables;


import java.util.Arrays;
import java.util.List;

import javax.annotation.Generated;

import main.java.nl.tue.ieis.is.basex.entities.jooq.BasexDatabase;
import main.java.nl.tue.ieis.is.basex.entities.jooq.Keys;
import main.java.nl.tue.ieis.is.basex.entities.jooq.tables.records.CostBenefitRecord;

import org.jooq.Field;
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
public class CostBenefit extends TableImpl<CostBenefitRecord> {

    private static final long serialVersionUID = 605216795;

    /**
     * The reference instance of <code>basex_database.cost_benefit</code>
     */
    public static final CostBenefit COST_BENEFIT = new CostBenefit();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<CostBenefitRecord> getRecordType() {
        return CostBenefitRecord.class;
    }

    /**
     * The column <code>basex_database.cost_benefit.CB_ID</code>.
     */
    public final TableField<CostBenefitRecord, String> CB_ID = createField("CB_ID", org.jooq.impl.SQLDataType.VARCHAR.length(40).nullable(false), this, "");

    /**
     * The column <code>basex_database.cost_benefit.NAME</code>.
     */
    public final TableField<CostBenefitRecord, String> NAME = createField("NAME", org.jooq.impl.SQLDataType.VARCHAR.length(50).nullable(false), this, "");

    /**
     * Create a <code>basex_database.cost_benefit</code> table reference
     */
    public CostBenefit() {
        this("cost_benefit", null);
    }

    /**
     * Create an aliased <code>basex_database.cost_benefit</code> table reference
     */
    public CostBenefit(String alias) {
        this(alias, COST_BENEFIT);
    }

    private CostBenefit(String alias, Table<CostBenefitRecord> aliased) {
        this(alias, aliased, null);
    }

    private CostBenefit(String alias, Table<CostBenefitRecord> aliased, Field<?>[] parameters) {
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
    public UniqueKey<CostBenefitRecord> getPrimaryKey() {
        return Keys.KEY_COST_BENEFIT_PRIMARY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UniqueKey<CostBenefitRecord>> getKeys() {
        return Arrays.<UniqueKey<CostBenefitRecord>>asList(Keys.KEY_COST_BENEFIT_PRIMARY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CostBenefit as(String alias) {
        return new CostBenefit(alias, this);
    }

    /**
     * Rename this table
     */
    public CostBenefit rename(String name) {
        return new CostBenefit(name, null);
    }
}
