package de.st_ddt.crazy.commandhelper;

import com.laytonsmith.annotations.api;
import com.laytonsmith.annotations.startup;
import com.laytonsmith.core.CHVersion;
import com.laytonsmith.core.Static;
import com.laytonsmith.core.constructs.CArray;
import com.laytonsmith.core.constructs.CString;
import com.laytonsmith.core.constructs.CVoid;
import com.laytonsmith.core.constructs.Construct;
import com.laytonsmith.core.constructs.Target;
import com.laytonsmith.core.environments.Environment;
import com.laytonsmith.core.exceptions.ConfigRuntimeException;
import com.laytonsmith.core.functions.AbstractFunction;
import com.laytonsmith.core.functions.Exceptions.ExceptionType;

import de.st_ddt.crazyutil.PreSetList;

public class CHCrazyCore
{

	@startup
	public static void onEnable()
	{
		try
		{
			Static.checkPlugin("CrazyCore", Target.UNKNOWN);
		}
		catch (final Exception e)
		{
			System.out.println("[CommandHelper] CHCrazyCore Could not find CrazyCore please make sure you have it installed.");
		}
		System.out.println("[CommandHelper] CHCrazyCore Initialized - Crazy");
	}

	@api
	public static class crazycore_get_presetlist extends AbstractFunction
	{

		public String getName()
		{
			return "crazycore_get_presetlist";
		}

		public Integer[] numArgs()
		{
			return new Integer[] { 1 };
		}

		public Construct exec(final Target t, final Environment env, final Construct... args) throws ConfigRuntimeException
		{
			Static.checkPlugin("CrazyCore", t);
			String key;
			final CArray list = new CArray(t);
			if (args[0] instanceof CString)
				key = args[0].val();
			else
				return new CVoid(t);
			final PreSetList preset = PreSetList.PRESETLISTS.get(key);
			if (preset == null)
				return list;
			for (final String entry : preset.getList())
				list.push(new CString(entry, t));
			return list;
		}

		public ExceptionType[] thrown()
		{
			return new ExceptionType[] { ExceptionType.InvalidPluginException };
		}

		public boolean isRestricted()
		{
			return true;
		}

		public Boolean runAsync()
		{
			return false;
		}

		public String docs()
		{
			return "array {presetlist} returns an array of entries in the given preset list.";
		}

		public CHVersion since()
		{
			return CHVersion.V3_3_1;
		}
	}
}
