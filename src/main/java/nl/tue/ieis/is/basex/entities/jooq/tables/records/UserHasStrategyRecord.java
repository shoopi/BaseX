/**
 * This class is generated by jOOQ
 */
package main.java.nl.tue.ieis.is.basex.entities.jooq.tables.records;


import javax.annotation.Generated;

import main.java.nl.tue.ieis.is.basex.entities.jooq.tables.UserHasStrategy;

import org.jooq.Field;
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
public class UserHasStrategyRecord extends UpdatableRecordImpl<UserHasStrategyRecord> implements Record2<String, String> {

    private static final long serialVersionUID = -449494870;

    /**
     * Setter for <code>basex_database.user_has_strategy.USER_USER_ID</code>.
     */
    public void setUserUserId(String value) {
        set(0, value);
    }

    /**
     * Getter for <code>basex_database.user_has_strategy.USER_USER_ID</code>.
     */
    public String getUserUserId() {
        return (String) get(0);
    }

    /**
     * Setter for <code>basex_database.user_has_strategy.STRATEGY_STRATEGY_ID</code>.
     */
    public void setStrategyStrategyId(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>basex_database.user_has_strategy.STRATEGY_STRATEGY_ID</code>.
     */
    public String getStrategyStrategyId() {
        return (String) get(1);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Record2<String, String> key() {
        return (Record2) super.key();
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
        return UserHasStrategy.USER_HAS_STRATEGY.USER_USER_ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field2() {
        return UserHasStrategy.USER_HAS_STRATEGY.STRATEGY_STRATEGY_ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value1() {
        return getUserUserId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value2() {
        return getStrategyStrategyId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UserHasStrategyRecord value1(String value) {
        setUserUserId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UserHasStrategyRecord value2(String value) {
        setStrategyStrategyId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UserHasStrategyRecord values(String value1, String value2) {
        value1(value1);
        value2(value2);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached UserHasStrategyRecord
     */
    public UserHasStrategyRecord() {
        super(UserHasStrategy.USER_HAS_STRATEGY);
    }

    /**
     * Create a detached, initialised UserHasStrategyRecord
     */
    public UserHasStrategyRecord(String userUserId, String strategyStrategyId) {
        super(UserHasStrategy.USER_HAS_STRATEGY);

        set(0, userUserId);
        set(1, strategyStrategyId);
    }
}
