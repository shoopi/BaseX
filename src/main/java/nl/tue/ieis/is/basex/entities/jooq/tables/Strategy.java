/**
 * This class is generated by jOOQ
 */
package main.java.nl.tue.ieis.is.basex.entities.jooq.tables;


import java.util.Arrays;
import java.util.List;

import javax.annotation.Generated;

import main.java.nl.tue.ieis.is.basex.entities.jooq.BasexDatabase;
import main.java.nl.tue.ieis.is.basex.entities.jooq.Keys;
import main.java.nl.tue.ieis.is.basex.entities.jooq.tables.records.StrategyRecord;

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
public class Strategy extends TableImpl<StrategyRecord> {

    private static final long serialVersionUID = 558784850;

    /**
     * The reference instance of <code>basex_database.strategy</code>
     */
    public static final Strategy STRATEGY = new Strategy();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<StrategyRecord> getRecordType() {
        return StrategyRecord.class;
    }

    /**
     * The column <code>basex_database.strategy.STRATEGY_ID</code>.
     */
    public final TableField<StrategyRecord, String> STRATEGY_ID = createField("STRATEGY_ID", org.jooq.impl.SQLDataType.VARCHAR.length(40).nullable(false), this, "");

    /**
     * The column <code>basex_database.strategy.STRATEGY_NAME</code>.
     */
    public final TableField<StrategyRecord, String> STRATEGY_NAME = createField("STRATEGY_NAME", org.jooq.impl.SQLDataType.VARCHAR.length(50).nullable(false), this, "");

    /**
     * The column <code>basex_database.strategy.STRATEGY_DESC</code>.
     */
    public final TableField<StrategyRecord, String> STRATEGY_DESC = createField("STRATEGY_DESC", org.jooq.impl.SQLDataType.VARCHAR.length(350), this, "");

    /**
     * The column <code>basex_database.strategy.VIU_CUSTOMER</code>.
     */
    public final TableField<StrategyRecord, String> VIU_CUSTOMER = createField("VIU_CUSTOMER", org.jooq.impl.SQLDataType.VARCHAR.length(550), this, "");

    /**
     * The column <code>basex_database.strategy.VIU_EXPERIENCE</code>.
     */
    public final TableField<StrategyRecord, String> VIU_EXPERIENCE = createField("VIU_EXPERIENCE", org.jooq.impl.SQLDataType.VARCHAR.length(550), this, "");

    /**
     * The column <code>basex_database.strategy.VIU_INTERACTIONS</code>.
     */
    public final TableField<StrategyRecord, String> VIU_INTERACTIONS = createField("VIU_INTERACTIONS", org.jooq.impl.SQLDataType.VARCHAR.length(550), this, "");

    /**
     * The column <code>basex_database.strategy.SES_CORE_SERVICES</code>.
     */
    public final TableField<StrategyRecord, String> SES_CORE_SERVICES = createField("SES_CORE_SERVICES", org.jooq.impl.SQLDataType.VARCHAR.length(550), this, "");

    /**
     * The column <code>basex_database.strategy.SES_CORE_PARTNERS</code>.
     */
    public final TableField<StrategyRecord, String> SES_CORE_PARTNERS = createField("SES_CORE_PARTNERS", org.jooq.impl.SQLDataType.VARCHAR.length(550), this, "");

    /**
     * The column <code>basex_database.strategy.SES_FOCAL_ORGANIZATION</code>.
     */
    public final TableField<StrategyRecord, String> SES_FOCAL_ORGANIZATION = createField("SES_FOCAL_ORGANIZATION", org.jooq.impl.SQLDataType.VARCHAR.length(550), this, "");

    /**
     * The column <code>basex_database.strategy.SES_ENRICHING_SERVICES</code>.
     */
    public final TableField<StrategyRecord, String> SES_ENRICHING_SERVICES = createField("SES_ENRICHING_SERVICES", org.jooq.impl.SQLDataType.VARCHAR.length(550), this, "");

    /**
     * The column <code>basex_database.strategy.SES_ENRICHING_PARTNERS</code>.
     */
    public final TableField<StrategyRecord, String> SES_ENRICHING_PARTNERS = createField("SES_ENRICHING_PARTNERS", org.jooq.impl.SQLDataType.VARCHAR.length(550), this, "");

    /**
     * The column <code>basex_database.strategy.CM_CORE_RELATIONSHIP</code>.
     */
    public final TableField<StrategyRecord, String> CM_CORE_RELATIONSHIP = createField("CM_CORE_RELATIONSHIP", org.jooq.impl.SQLDataType.VARCHAR.length(550), this, "");

    /**
     * The column <code>basex_database.strategy.CM_ENRICHING_RELATIONSHIPS</code>.
     */
    public final TableField<StrategyRecord, String> CM_ENRICHING_RELATIONSHIPS = createField("CM_ENRICHING_RELATIONSHIPS", org.jooq.impl.SQLDataType.VARCHAR.length(550), this, "");

    /**
     * Create a <code>basex_database.strategy</code> table reference
     */
    public Strategy() {
        this("strategy", null);
    }

    /**
     * Create an aliased <code>basex_database.strategy</code> table reference
     */
    public Strategy(String alias) {
        this(alias, STRATEGY);
    }

    private Strategy(String alias, Table<StrategyRecord> aliased) {
        this(alias, aliased, null);
    }

    private Strategy(String alias, Table<StrategyRecord> aliased, Field<?>[] parameters) {
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
    public UniqueKey<StrategyRecord> getPrimaryKey() {
        return Keys.KEY_STRATEGY_PRIMARY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UniqueKey<StrategyRecord>> getKeys() {
        return Arrays.<UniqueKey<StrategyRecord>>asList(Keys.KEY_STRATEGY_PRIMARY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Strategy as(String alias) {
        return new Strategy(alias, this);
    }

    /**
     * Rename this table
     */
    public Strategy rename(String name) {
        return new Strategy(name, null);
    }
}