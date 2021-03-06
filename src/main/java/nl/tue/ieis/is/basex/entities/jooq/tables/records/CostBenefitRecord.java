/**
 * This class is generated by jOOQ
 */
package main.java.nl.tue.ieis.is.basex.entities.jooq.tables.records;


import javax.annotation.Generated;

import main.java.nl.tue.ieis.is.basex.entities.jooq.tables.CostBenefit;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record2;
import org.jooq.Row2;
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
public class CostBenefitRecord extends UpdatableRecordImpl<CostBenefitRecord> implements Record2<String, String> {

    private static final long serialVersionUID = -197160644;

    /**
     * Setter for <code>basex_database.cost_benefit.CB_ID</code>.
     */
    public void setCbId(String value) {
        set(0, value);
    }

    /**
     * Getter for <code>basex_database.cost_benefit.CB_ID</code>.
     */
    public String getCbId() {
        return (String) get(0);
    }

    /**
     * Setter for <code>basex_database.cost_benefit.NAME</code>.
     */
    public void setName(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>basex_database.cost_benefit.NAME</code>.
     */
    public String getName() {
        return (String) get(1);
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
    // Record2 type implementation
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Row2<String, String> fieldsRow() {
        return (Row2) super.fieldsRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Row2<String, String> valuesRow() {
        return (Row2) super.valuesRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field1() {
        return CostBenefit.COST_BENEFIT.CB_ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field2() {
        return CostBenefit.COST_BENEFIT.NAME;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value1() {
        return getCbId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value2() {
        return getName();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CostBenefitRecord value1(String value) {
        setCbId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CostBenefitRecord value2(String value) {
        setName(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CostBenefitRecord values(String value1, String value2) {
        value1(value1);
        value2(value2);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached CostBenefitRecord
     */
    public CostBenefitRecord() {
        super(CostBenefit.COST_BENEFIT);
    }

    /**
     * Create a detached, initialised CostBenefitRecord
     */
    public CostBenefitRecord(String cbId, String name) {
        super(CostBenefit.COST_BENEFIT);

        set(0, cbId);
        set(1, name);
    }
}
