/**
 * This class is generated by jOOQ
 */
package main.java.nl.tue.ieis.is.basex.entities.jooq.tables.records;


import javax.annotation.Generated;

import main.java.nl.tue.ieis.is.basex.entities.jooq.tables.Actor;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record3;
import org.jooq.Row3;
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
public class ActorRecord extends UpdatableRecordImpl<ActorRecord> implements Record3<String, String, String> {

    private static final long serialVersionUID = -1318371295;

    /**
     * Setter for <code>basex_database.actor.ACTOR_ID</code>.
     */
    public void setActorId(String value) {
        set(0, value);
    }

    /**
     * Getter for <code>basex_database.actor.ACTOR_ID</code>.
     */
    public String getActorId() {
        return (String) get(0);
    }

    /**
     * Setter for <code>basex_database.actor.ACTOR_NAME</code>.
     */
    public void setActorName(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>basex_database.actor.ACTOR_NAME</code>.
     */
    public String getActorName() {
        return (String) get(1);
    }

    /**
     * Setter for <code>basex_database.actor.ACTOR_DESC</code>.
     */
    public void setActorDesc(String value) {
        set(2, value);
    }

    /**
     * Getter for <code>basex_database.actor.ACTOR_DESC</code>.
     */
    public String getActorDesc() {
        return (String) get(2);
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
    // Record3 type implementation
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Row3<String, String, String> fieldsRow() {
        return (Row3) super.fieldsRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Row3<String, String, String> valuesRow() {
        return (Row3) super.valuesRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field1() {
        return Actor.ACTOR.ACTOR_ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field2() {
        return Actor.ACTOR.ACTOR_NAME;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field3() {
        return Actor.ACTOR.ACTOR_DESC;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value1() {
        return getActorId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value2() {
        return getActorName();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value3() {
        return getActorDesc();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ActorRecord value1(String value) {
        setActorId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ActorRecord value2(String value) {
        setActorName(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ActorRecord value3(String value) {
        setActorDesc(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ActorRecord values(String value1, String value2, String value3) {
        value1(value1);
        value2(value2);
        value3(value3);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached ActorRecord
     */
    public ActorRecord() {
        super(Actor.ACTOR);
    }

    /**
     * Create a detached, initialised ActorRecord
     */
    public ActorRecord(String actorId, String actorName, String actorDesc) {
        super(Actor.ACTOR);

        set(0, actorId);
        set(1, actorName);
        set(2, actorDesc);
    }
}
