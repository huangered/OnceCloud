/*
 * Copyright (c) Citrix Systems, Inc.
 *
 * This library is free software; you can redistribute it and/or modify it
 * under the terms of version 2 of the GNU General Public License as published
 * by the Free Software Foundation, with the additional linking exception as
 * follows:
 * 
 *   Linking this library statically or dynamically with other modules is
 *   making a combined work based on this library. Thus, the terms and
 *   conditions of the GNU General Public License cover the whole combination.
 * 
 *   As a special exception, the copyright holders of this library give you
 *   permission to link this library with independent modules to produce an
 *   executable, regardless of the license terms of these independent modules,
 *   and to copy and distribute the resulting executable under terms of your
 *   choice, provided that you also meet, for each linked independent module,
 *   the terms and conditions of the license of that module. An independent
 *   module is a module which is not derived from or based on this library. If
 *   you modify this library, you may extend this exception to your version of
 *   the library, but you are not obligated to do so. If you do not wish to do
 *   so, delete this exception statement from your version.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License for
 * more details.
 * 
 * You should have received a copy of the GNU General Public License along
 * with this program; if not, write to the Free Software Foundation, Inc., 51
 * Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 */

package org.Xen.API;

import org.Xen.API.Types.BadServerResponse;
import org.Xen.API.Types.XenAPIException;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.xmlrpc.XmlRpcException;

/**
 * A console
 * 
 * @author Citrix Systems, Inc.
 */
public class Console extends XenAPIObject {

	/**
	 * The XenAPI reference to this object.
	 */
	protected final String ref;

	/**
	 * For internal use only.
	 */
	Console(String ref) {
		this.ref = ref;
	}

	public String toWireString() {
		return this.ref;
	}

	/**
	 * If obj is a Console, compares XenAPI references for equality.
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj != null && obj instanceof Console) {
			Console other = (Console) obj;
			return other.ref.equals(this.ref);
		} else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return ref.hashCode();
	}

	/**
	 * Represents all the fields in a Console
	 */
	public static class Record implements Types.Record {
		public String toString() {
			StringWriter writer = new StringWriter();
			PrintWriter print = new PrintWriter(writer);
			print.printf("%1$20s: %2$s\n", "uuid", this.uuid);
			print.printf("%1$20s: %2$s\n", "protocol", this.protocol);
			print.printf("%1$20s: %2$s\n", "location", this.location);
			print.printf("%1$20s: %2$s\n", "VM", this.VM);
			print.printf("%1$20s: %2$s\n", "otherConfig", this.otherConfig);
			return writer.toString();
		}

		/**
		 * Convert a console.Record to a Map
		 */
		public Map<String, Object> toMap() {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("uuid", this.uuid == null ? "" : this.uuid);
			map.put("protocol",
					this.protocol == null ? Types.ConsoleProtocol.UNRECOGNIZED
							: this.protocol);
			map.put("location", this.location == null ? "" : this.location);
			map.put("VM", this.VM == null ? new VM("OpaqueRef:NULL") : this.VM);
			map.put("other_config",
					this.otherConfig == null ? new HashMap<String, String>()
							: this.otherConfig);
			return map;
		}

		/**
		 * Unique identifier/object reference
		 */
		public String uuid;
		/**
		 * the protocol used by this console
		 */
		public Types.ConsoleProtocol protocol;
		/**
		 * URI for the console service
		 */
		public String location;
		/**
		 * VM to which this console is attached
		 */
		public VM VM;
		/**
		 * additional configuration
		 */
		public Map<String, String> otherConfig;
	}

	/**
	 * Get a record containing the current state of the given console.
	 * 
	 * @return all fields from the object
	 */
	public Console.Record getRecord(Connection c) throws BadServerResponse,
			XenAPIException, XmlRpcException {
		String method_call = "console.get_record";
		String session = c.getSessionReference();
		Object[] method_params = { Marshalling.toXMLRPC(session),
				Marshalling.toXMLRPC(this.ref) };
		Map<?, ?> response = c.dispatch(method_call, method_params);
		Object result = response.get("Value");
		return Types.toConsoleRecord(result);
	}

	/**
	 * Get a reference to the console instance with the specified UUID.
	 * 
	 * @param uuid
	 *            UUID of object to return
	 * @return reference to the object
	 */
	public static Console getByUuid(Connection c, String uuid)
			throws BadServerResponse, XenAPIException, XmlRpcException {
		String method_call = "console.get_by_uuid";
		String session = c.getSessionReference();
		Object[] method_params = { Marshalling.toXMLRPC(session),
				Marshalling.toXMLRPC(uuid) };
		Map<?, ?> response = c.dispatch(method_call, method_params);
		Object result = response.get("Value");
		return Types.toConsole(result);
	}


	/**
	 * Create a new console instance, and return its handle.
	 * 
	 * @param record
	 *            All constructor arguments
	 * @return reference to the newly created object
	 */
	public static Console create(Connection c, Console.Record record)
			throws BadServerResponse, XenAPIException, XmlRpcException {
		String method_call = "console.create";
		String session = c.getSessionReference();
		Map<String, Object> record_map = record.toMap();
		Object[] method_params = { Marshalling.toXMLRPC(session),
				Marshalling.toXMLRPC(record_map) };
		Map<?, ?> response = c.dispatch(method_call, method_params);
		Object result = response.get("Value");
		return Types.toConsole(result);
	}
	
	public static Console createOn(Connection c, Console.Record record, Host host)
			throws BadServerResponse, XenAPIException, XmlRpcException {
		String method_call = "console.create_on";
		String session = c.getSessionReference();
		Map<String, Object> record_map = record.toMap();
		Object[] method_params = { Marshalling.toXMLRPC(session),
				Marshalling.toXMLRPC(record_map), Marshalling.toXMLRPC(host) };
		Map<?, ?> response = c.dispatch(method_call, method_params);
		Object result = response.get("Value");
		return Types.toConsole(result);
	}


	/**
	 * Destroy the specified console instance.
	 * 
	 */
	public void destroy(Connection c) throws BadServerResponse,
			XenAPIException, XmlRpcException {
		String method_call = "console.destroy";
		String session = c.getSessionReference();
		Object[] method_params = { Marshalling.toXMLRPC(session),
				Marshalling.toXMLRPC(this.ref) };
		c.dispatch(method_call, method_params);
		return;
	}

	/**
	 * Get the uuid field of the given console.
	 * 
	 * @return value of the field
	 */
	public String getUuid(Connection c) throws BadServerResponse,
			XenAPIException, XmlRpcException {
		String method_call = "console.get_uuid";
		String session = c.getSessionReference();
		Object[] method_params = { Marshalling.toXMLRPC(session),
				Marshalling.toXMLRPC(this.ref) };
		Map<?, ?> response = c.dispatch(method_call, method_params);
		Object result = response.get("Value");
		return Types.toString(result);
	}

	/**
	 * Get the protocol field of the given console.
	 * 
	 * @return value of the field
	 */
	public Types.ConsoleProtocol getProtocol(Connection c)
			throws BadServerResponse, XenAPIException, XmlRpcException {
		String method_call = "console.get_protocol";
		String session = c.getSessionReference();
		Object[] method_params = { Marshalling.toXMLRPC(session),
				Marshalling.toXMLRPC(this.ref) };
		Map<?, ?> response = c.dispatch(method_call, method_params);
		Object result = response.get("Value");
		return Types.toConsoleProtocol(result);
	}

	/**
	 * Get the VM field of the given console.
	 * 
	 * @return value of the field
	 */
	public VM getVM(Connection c) throws BadServerResponse, XenAPIException,
			XmlRpcException {
		String method_call = "console.get_VM";
		String session = c.getSessionReference();
		Object[] method_params = { Marshalling.toXMLRPC(session),
				Marshalling.toXMLRPC(this.ref) };
		Map<?, ?> response = c.dispatch(method_call, method_params);
		Object result = response.get("Value");
		return Types.toVM(result);
	}

	/**
	 * Get the other_config field of the given console.
	 * 
	 * @return value of the field
	 */
	public Map<String, String> getOtherConfig(Connection c)
			throws BadServerResponse, XenAPIException, XmlRpcException {
		String method_call = "console.get_other_config";
		String session = c.getSessionReference();
		Object[] method_params = { Marshalling.toXMLRPC(session),
				Marshalling.toXMLRPC(this.ref) };
		Map<?, ?> response = c.dispatch(method_call, method_params);
		Object result = response.get("Value");
		return Types.toMapOfStringString(result);
	}

	/**
	 * Set the other_config field of the given console.
	 * 
	 * @param otherConfig
	 *            New value to set
	 */
	public void setOtherConfig(Connection c, Map<String, String> otherConfig)
			throws BadServerResponse, XenAPIException, XmlRpcException {
		String method_call = "console.set_other_config";
		String session = c.getSessionReference();
		Object[] method_params = { Marshalling.toXMLRPC(session),
				Marshalling.toXMLRPC(this.ref),
				Marshalling.toXMLRPC(otherConfig) };
		c.dispatch(method_call, method_params);
		return;
	}

	/**
	 * Add the given key-value pair to the other_config field of the given
	 * console.
	 * 
	 * @param key
	 *            Key to add
	 * @param value
	 *            Value to add
	 */
	public void addToOtherConfig(Connection c, String key, String value)
			throws BadServerResponse, XenAPIException, XmlRpcException {
		String method_call = "console.add_to_other_config";
		String session = c.getSessionReference();
		Object[] method_params = { Marshalling.toXMLRPC(session),
				Marshalling.toXMLRPC(this.ref), Marshalling.toXMLRPC(key),
				Marshalling.toXMLRPC(value) };
		c.dispatch(method_call, method_params);
		return;
	}

	/**
	 * Remove the given key and its corresponding value from the other_config
	 * field of the given console. If the key is not in that Map, then do
	 * nothing.
	 * 
	 * @param key
	 *            Key to remove
	 */
	public void removeFromOtherConfig(Connection c, String key)
			throws BadServerResponse, XenAPIException, XmlRpcException {
		String method_call = "console.remove_from_other_config";
		String session = c.getSessionReference();
		Object[] method_params = { Marshalling.toXMLRPC(session),
				Marshalling.toXMLRPC(this.ref), Marshalling.toXMLRPC(key) };
		c.dispatch(method_call, method_params);
		return;
	}

	/**
	 * Return a list of all the consoles known to the system.
	 * 
	 * @return references to all objects
	 */
	public static Set<Console> getAll(Connection c) throws BadServerResponse,
			XenAPIException, XmlRpcException {
		String method_call = "console.get_all";
		String session = c.getSessionReference();
		Object[] method_params = { Marshalling.toXMLRPC(session) };
		Map<?, ?> response = c.dispatch(method_call, method_params);
		Object result = response.get("Value");
		return Types.toSetOfConsole(result);
	}

	/**
	 * Return a map of console references to console records for all consoles
	 * known to the system.
	 * 
	 * @return records of all objects
	 */
	public static Map<Console, Console.Record> getAllRecords(Connection c)
			throws BadServerResponse, XenAPIException, XmlRpcException {
		String method_call = "console.get_all_records";
		String session = c.getSessionReference();
		Object[] method_params = { Marshalling.toXMLRPC(session) };
		Map<?, ?> response = c.dispatch(method_call, method_params);
		Object result = response.get("Value");
		return Types.toMapOfConsoleConsoleRecord(result);
	}

}