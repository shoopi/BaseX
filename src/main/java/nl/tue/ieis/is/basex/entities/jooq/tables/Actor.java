/**
 * This class is generated by jOOQ
 */
package main.java.nl.tue.ieis.is.basex.entities.jooq.tables;


import java.util.Arrays;
import java.util.List;

import javax.annotation.Generated;

import main.java.nl.tue.ieis.is.basex.entities.jooq.BasexDatabase;
import main.java.nl.tue.ieis.is.basex.entities.jooq.Keys;
import main.java.nl.tue.ieis.is.basex.entities.jooq.tables.records.ActorRecord;

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
public class Actor extends TableImpl<ActorRecord> {

    private static final long serialVersionUID = 1727659572;

    /**
     * The reference instance of <code>basex_database.actor</code>
     */
    public static final Actor ACTOR = new Actor();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<ActorRecord> getRecordType() {
        return ActorRecord.class;
    }

    /**
     * The column <code>basex_database.actor.ACTOR_ID</code>.
     */
    public final TableField<ActorRecord, String> ACTOR_ID = createField("ACTOR_ID", org.jooq.impl.SQLDataType.VARCHAR.length(40).nullable(false), this, "");

    /**
     * The column <code>basex_database.actor.ACTOR_NAME</code>.
     */
    public final TableField<ActorRecord, String> ACTOR_NAME = createField("ACTOR_NAME", org.jooq.impl.SQLDataType.VARCHAR.length(150).nullable(false), this, "");

    /**
     * The column <code>basex_database.actor.ACTOR_DESC</code>.
     */
    public final TableField<ActorRecord, String> ACTOR_DESC = createField("ACTOR_DESC", org.jooq.impl.SQLDataType.VARCHAR.length(500), this, "");

    /**
     * Create a <code>basex_database.actor</code> table reference
     */
    public Actor() {
        this("actor", null);
    }

    /**
     * Create an aliased <code>basex_database.actor</code> table reference
     */
    public Actor(String alias) {
        this(alias, ACTOR);
    }

    private Actor(String alias, Table<ActorRecord> aliased) {
        this(alias, aliased, null);
    }

    private Actor(String alias, Table<ActorRecord> aliased, Field<?>[] parameters) {
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
    public UniqueKey<ActorRecord> getPrimaryKey() {
        return Keys.KEY_ACTOR_PRIMARY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UniqueKey<ActorRecord>> getKeys() {
        return Arrays.<UniqueKey<ActorRecord>>asList(Keys.KEY_ACTOR_PRIMARY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Actor as(String alias) {
        return new Actor(alias, this);
    }

    /**
     * Rename this table
     */
    public Actor rename(String name) {
        return new Actor(name, null);
    }
}