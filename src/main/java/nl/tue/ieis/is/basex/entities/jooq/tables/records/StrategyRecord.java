/**
 * This class is generated by jOOQ
 */
package main.java.nl.tue.ieis.is.basex.entities.jooq.tables.records;


import javax.annotation.Generated;

import main.java.nl.tue.ieis.is.basex.entities.jooq.tables.Strategy;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record13;
import org.jooq.Row13;
import org.jooq.impl.UpdatableRecordImpl;


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
public class StrategyRecord extends UpdatableRecordImpl<StrategyRecord> implements Record13<String, String, String, String, String, String, String, String, String, String, String, String, String> {

    private static final long serialVersionUID = 1303080299;

    /**
     * Setter for <code>basex_database.strategy.STRATEGY_ID</code>.
     */
    public void setStrategyId(String value) {
        set(0, value);
    }

    /**
     * Getter for <code>basex_database.strategy.STRATEGY_ID</code>.
     */
    public String getStrategyId() {
        return (String) get(0);
    }

    /**
     * Setter for <code>basex_database.strategy.STRATEGY_NAME</code>.
     */
    public void setStrategyName(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>basex_database.strategy.STRATEGY_NAME</code>.
     */
    public String getStrategyName() {
        return (String) get(1);
    }

    /**
     * Setter for <code>basex_database.strategy.STRATEGY_DESC</code>.
     */
    public void setStrategyDesc(String value) {
        set(2, value);
    }

    /**
     * Getter for <code>basex_database.strategy.STRATEGY_DESC</code>.
     */
    public String getStrategyDesc() {
        return (String) get(2);
    }

    /**
     * Setter for <code>basex_database.strategy.VIU_CUSTOMER</code>.
     */
    public void setViuCustomer(String value) {
        set(3, value);
    }

    /**
     * Getter for <code>basex_database.strategy.VIU_CUSTOMER</code>.
     */
    public String getViuCustomer() {
        return (String) get(3);
    }

    /**
     * Setter for <code>basex_database.strategy.VIU_EXPERIENCE</code>.
     */
    public void setViuExperience(String value) {
        set(4, value);
    }

    /**
     * Getter for <code>basex_database.strategy.VIU_EXPERIENCE</code>.
     */
    public String getViuExperience() {
        return (String) get(4);
    }

    /**
     * Setter for <code>basex_database.strategy.VIU_INTERACTIONS</code>.
     */
    public void setViuInteractions(String value) {
        set(5, value);
    }

    /**
     * Getter for <code>basex_database.strategy.VIU_INTERACTIONS</code>.
     */
    public String getViuInteractions() {
        return (String) get(5);
    }

    /**
     * Setter for <code>basex_database.strategy.SES_CORE_SERVICES</code>.
     */
    public void setSesCoreServices(String value) {
        set(6, value);
    }

    /**
     * Getter for <code>basex_database.strategy.SES_CORE_SERVICES</code>.
     */
    public String getSesCoreServices() {
        return (String) get(6);
    }

    /**
     * Setter for <code>basex_database.strategy.SES_CORE_PARTNERS</code>.
     */
    public void setSesCorePartners(String value) {
        set(7, value);
    }

    /**
     * Getter for <code>basex_database.strategy.SES_CORE_PARTNERS</code>.
     */
    public String getSesCorePartners() {
        return (String) get(7);
    }

    /**
     * Setter for <code>basex_database.strategy.SES_FOCAL_ORGANIZATION</code>.
     */
    public void setSesFocalOrganization(String value) {
        set(8, value);
    }

    /**
     * Getter for <code>basex_database.strategy.SES_FOCAL_ORGANIZATION</code>.
     */
    public String getSesFocalOrganization() {
        return (String) get(8);
    }

    /**
     * Setter for <code>basex_database.strategy.SES_ENRICHING_SERVICES</code>.
     */
    public void setSesEnrichingServices(String value) {
        set(9, value);
    }

    /**
     * Getter for <code>basex_database.strategy.SES_ENRICHING_SERVICES</code>.
     */
    public String getSesEnrichingServices() {
        return (String) get(9);
    }

    /**
     * Setter for <code>basex_database.strategy.SES_ENRICHING_PARTNERS</code>.
     */
    public void setSesEnrichingPartners(String value) {
        set(10, value);
    }

    /**
     * Getter for <code>basex_database.strategy.SES_ENRICHING_PARTNERS</code>.
     */
    public String getSesEnrichingPartners() {
        return (String) get(10);
    }

    /**
     * Setter for <code>basex_database.strategy.CM_CORE_RELATIONSHIP</code>.
     */
    public void setCmCoreRelationship(String value) {
        set(11, value);
    }

    /**
     * Getter for <code>basex_database.strategy.CM_CORE_RELATIONSHIP</code>.
     */
    public String getCmCoreRelationship() {
        return (String) get(11);
    }

    /**
     * Setter for <code>basex_database.strategy.CM_ENRICHING_RELATIONSHIPS</code>.
     */
    public void setCmEnrichingRelationships(String value) {
        set(12, value);
    }

    /**
     * Getter for <code>basex_database.strategy.CM_ENRICHING_RELATIONSHIPS</code>.
     */
    public String getCmEnrichingRelationships() {
        return (String) get(12);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Record1<String> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record13 type implementation
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Row13<String, String, String, String, String, String, String, String, String, String, String, String, String> fieldsRow() {
        return (Row13) super.fieldsRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Row13<String, String, String, String, String, String, String, String, String, String, String, String, String> valuesRow() {
        return (Row13) super.valuesRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field1() {
        return Strategy.STRATEGY.STRATEGY_ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field2() {
        return Strategy.STRATEGY.STRATEGY_NAME;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field3() {
        return Strategy.STRATEGY.STRATEGY_DESC;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field4() {
        return Strategy.STRATEGY.VIU_CUSTOMER;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field5() {
        return Strategy.STRATEGY.VIU_EXPERIENCE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field6() {
        return Strategy.STRATEGY.VIU_INTERACTIONS;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field7() {
        return Strategy.STRATEGY.SES_CORE_SERVICES;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field8() {
        return Strategy.STRATEGY.SES_CORE_PARTNERS;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field9() {
        return Strategy.STRATEGY.SES_FOCAL_ORGANIZATION;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field10() {
        return Strategy.STRATEGY.SES_ENRICHING_SERVICES;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field11() {
        return Strategy.STRATEGY.SES_ENRICHING_PARTNERS;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field12() {
        return Strategy.STRATEGY.CM_CORE_RELATIONSHIP;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field13() {
        return Strategy.STRATEGY.CM_ENRICHING_RELATIONSHIPS;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value1() {
        return getStrategyId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value2() {
        return getStrategyName();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value3() {
        return getStrategyDesc();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value4() {
        return getViuCustomer();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value5() {
        return getViuExperience();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value6() {
        return getViuInteractions();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value7() {
        return getSesCoreServices();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value8() {
        return getSesCorePartners();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value9() {
        return getSesFocalOrganization();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value10() {
        return getSesEnrichingServices();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value11() {
        return getSesEnrichingPartners();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value12() {
        return getCmCoreRelationship();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value13() {
        return getCmEnrichingRelationships();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public StrategyRecord value1(String value) {
        setStrategyId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public StrategyRecord value2(String value) {
        setStrategyName(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public StrategyRecord value3(String value) {
        setStrategyDesc(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public StrategyRecord value4(String value) {
        setViuCustomer(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public StrategyRecord value5(String value) {
        setViuExperience(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public StrategyRecord value6(String value) {
        setViuInteractions(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public StrategyRecord value7(String value) {
        setSesCoreServices(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public StrategyRecord value8(String value) {
        setSesCorePartners(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public StrategyRecord value9(String value) {
        setSesFocalOrganization(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public StrategyRecord value10(String value) {
        setSesEnrichingServices(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public StrategyRecord value11(String value) {
        setSesEnrichingPartners(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public StrategyRecord value12(String value) {
        setCmCoreRelationship(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public StrategyRecord value13(String value) {
        setCmEnrichingRelationships(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public StrategyRecord values(String value1, String value2, String value3, String value4, String value5, String value6, String value7, String value8, String value9, String value10, String value11, String value12, String value13) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        value6(value6);
        value7(value7);
        value8(value8);
        value9(value9);
        value10(value10);
        value11(value11);
        value12(value12);
        value13(value13);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached StrategyRecord
     */
    public StrategyRecord() {
        super(Strategy.STRATEGY);
    }

    /**
     * Create a detached, initialised StrategyRecord
     */
    public StrategyRecord(String strategyId, String strategyName, String strategyDesc, String viuCustomer, String viuExperience, String viuInteractions, String sesCoreServices, String sesCorePartners, String sesFocalOrganization, String sesEnrichingServices, String sesEnrichingPartners, String cmCoreRelationship, String cmEnrichingRelationships) {
        super(Strategy.STRATEGY);

        set(0, strategyId);
        set(1, strategyName);
        set(2, strategyDesc);
        set(3, viuCustomer);
        set(4, viuExperience);
        set(5, viuInteractions);
        set(6, sesCoreServices);
        set(7, sesCorePartners);
        set(8, sesFocalOrganization);
        set(9, sesEnrichingServices);
        set(10, sesEnrichingPartners);
        set(11, cmCoreRelationship);
        set(12, cmEnrichingRelationships);
    }
}