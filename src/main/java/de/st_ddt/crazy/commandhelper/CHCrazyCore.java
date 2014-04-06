package de.st_ddt.crazy.commandhelper;

import com.laytonsmith.PureUtilities.SimpleVersion;
import com.laytonsmith.PureUtilities.Version;
import com.laytonsmith.annotations.api;
import com.laytonsmith.core.CHVersion;
import com.laytonsmith.core.Static;
import com.laytonsmith.core.constructs.CArray;
import com.laytonsmith.core.constructs.CString;
import com.laytonsmith.core.constructs.CVoid;
import com.laytonsmith.core.constructs.Construct;
import com.laytonsmith.core.constructs.Target;
import com.laytonsmith.core.environments.Environment;
import com.laytonsmith.core.exceptions.ConfigRuntimeException;
import com.laytonsmith.core.extensions.AbstractExtension;
import com.laytonsmith.core.functions.AbstractFunction;
import com.laytonsmith.core.functions.Exceptions.ExceptionType;

import de.st_ddt.crazyutil.PreSetList;

public class CHCrazyCore extends AbstractExtension
{

	public static void onEnable()
	{
		try
		{
			Static.checkPlugin("CrazyCore", Target.UNKNOWN);
		}
		catch (final Exception e)
		{
			System.err.println("[CommandHelper] CHCrazyCore Could not find CrazyCore please make sure you have it installed.");
		}
		System.out.println("[CommandHelper] CHCrazyCore Initialized - Crazy");
	}

	@Override
	public void onShutdown()
	{
		System.out.println("[CommandHelper] CHCrazyCore Shutdown - Crazy");
	}

	@Override
	public Version getVersion()
	{
		return new SimpleVersion(1, 1, 0);
	}

	@api
	public static class crazycore_get_presetlist extends AbstractFunction
	{

		@Override
		public String getName()
		{
			return "crazycore_get_presetlist";
		}

		@Override
		public Integer[] numArgs()
		{
			return new Integer[] { 1 };
		}

		@Override
		public Construct exec(final Target t, final Environment env, final Construct... args) throws ConfigRuntimeException
		{
			Static.checkPlugin("CrazyCore", t);
			String key;
			final CArray list = new CArray(t);
			if (args[0] instanceof CString)
				key = args[0].val();
			else
				return CVoid.GenerateCVoid(t);
			final PreSetList preset = PreSetList.PRESETLISTS.get(key);
			if (preset == null)
				return list;
			for (final String entry : preset.getList())
				list.push(new CString(entry, t));
			return list;
		}

		@Override
		public ExceptionType[] thrown()
		{
			return new ExceptionType[] { ExceptionType.InvalidPluginException };
		}

		@Override
		public boolean isRestricted()
		{
			return true;
		}

		@Override
		public Boolean runAsync()
		{
			return false;
		}

		@Override
		public String docs()
		{
			return "array {presetlist} returns an array of entries in the given preset list.";
		}

		@Override
		public CHVersion since()
		{
			return CHVersion.V3_3_1;
		}
	}
}
