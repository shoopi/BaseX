/**
 * This class is generated by jOOQ
 */
package main.java.nl.tue.ieis.is.basex.entities.jooq.tables;


import java.util.Arrays;
import java.util.List;

import javax.annotation.Generated;

import main.java.nl.tue.ieis.is.basex.entities.jooq.BasexDatabase;
import main.java.nl.tue.ieis.is.basex.entities.jooq.Keys;
import main.java.nl.tue.ieis.is.basex.entities.jooq.tables.records.LocationGuiRecord;

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
public class LocationGui extends TableImpl<LocationGuiRecord> {

    private static final long serialVersionUID = -1098145336;

    /**
     * The reference instance of <code>basex_database.location_gui</code>
     */
    public static final LocationGui LOCATION_GUI = new LocationGui();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<LocationGuiRecord> getRecordType() {
        return LocationGuiRecord.class;
    }

    /**
     * The column <code>basex_database.location_gui.COMPONENT_ID</code>.
     */
    public final TableField<LocationGuiRecord, String> COMPONENT_ID = createField("COMPONENT_ID", org.jooq.impl.SQLDataType.VARCHAR.length(50).nullable(false), this, "");

    /**
     * The column <code>basex_database.location_gui.BM_ID</code>.
     */
    public final TableField<LocationGuiRecord, String> BM_ID = createField("BM_ID", org.jooq.impl.SQLDataType.VARCHAR.length(40).nullable(false), this, "");

    /**
     * The column <code>basex_database.location_gui.BM_ACTOR_ID</code>.
     */
    public final TableField<LocationGuiRecord, String> BM_ACTOR_ID = createField("BM_ACTOR_ID", org.jooq.impl.SQLDataType.VARCHAR.length(40).nullable(false), this, "");

    /**
     * The column <code>basex_database.location_gui.X</code>.
     */
    public final TableField<LocationGuiRecord, Double> X = createField("X", org.jooq.impl.SQLDataType.DOUBLE.nullable(false), this, "");

    /**
     * The column <code>basex_database.location_gui.Y</code>.
     */
    public final TableField<LocationGuiRecord, Double> Y = createField("Y", org.jooq.impl.SQLDataType.DOUBLE.nullable(false), this, "");

    /**
     * The column <code>basex_database.location_gui.Degree</code>.
     */
    public final TableField<LocationGuiRecord, Double> DEGREE = createField("Degree", org.jooq.impl.SQLDataType.DOUBLE.nullable(false), this, "");

    /**
     * Create a <code>basex_database.location_gui</code> table reference
     */
    public LocationGui() {
        this("location_gui", null);
    }

    /**
     * Create an aliased <code>basex_database.location_gui</code> table reference
     */
    public LocationGui(String alias) {
        this(alias, LOCATION_GUI);
    }

    private LocationGui(String alias, Table<LocationGuiRecord> aliased) {
        this(alias, aliased, null);
    }

    private LocationGui(String alias, Table<LocationGuiRecord> aliased, Field<?>[] parameters) {
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
    public UniqueKey<LocationGuiRecord> getPrimaryKey() {
        return Keys.KEY_LOCATION_GUI_PRIMARY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UniqueKey<LocationGuiRecord>> getKeys() {
        return Arrays.<UniqueKey<LocationGuiRecord>>asList(Keys.KEY_LOCATION_GUI_PRIMARY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LocationGui as(String alias) {
        return new LocationGui(alias, this);
    }

    /**
     * Rename this table
     */
    public LocationGui rename(String name) {
        return new LocationGui(name, null);
    }
}
