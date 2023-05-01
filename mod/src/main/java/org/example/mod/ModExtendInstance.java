package org.example.mod;

import mb.spoofax.core.language.cli.CliCommand;
import mb.spoofax.core.language.cli.CliParam;
import mb.spoofax.core.language.command.AutoCommandRequest;
import mb.spoofax.core.language.command.CommandDef;
import mb.spoofax.core.language.taskdef.NoneHoverTaskDef;
import mb.spoofax.core.language.taskdef.NoneResolveTaskDef;
import org.example.mod.command.ModShowAnalyzedAstCommand;
import org.example.mod.command.ModShowParsedAstCommand;
import org.example.mod.command.ModShowParsedTokensCommand;
import org.example.mod.command.ModShowPreAnalyzeAstCommand;
import org.example.mod.command.ModShowScopeGraphAstCommand;
import org.example.mod.command.ModShowScopeGraphCommand;
import org.example.mod.task.ModAnalyze;
import org.example.mod.task.ModCheckDeaggregator;
import org.example.mod.task.ModCheckMulti;
import org.example.mod.task.ModParse;
import org.example.mod.task.ModStyle;
import org.example.mod.task.ModTestStrategoTaskDef;
import org.example.mod.task.ModTokenize;

import javax.inject.Inject;
import java.util.Set;

public class ModExtendInstance extends ModInstance {
    private final ModShowParsedAstCommand modShowParsedAstCommand;
    private final ModShowAnalyzedAstCommand modShowAnalyzedAstCommand;

    // NOTE: this constructor needs to be kept up to date with the one in ModInstance
    @Inject public ModExtendInstance(
        ModParse modParse,
        ModTokenize modTokenize,
        ModCheckMulti modCheckMulti,
        ModCheckDeaggregator modCheckDeaggregator,
        ModStyle modStyle,
        NoneResolveTaskDef noneResolveTaskDef,
        NoneHoverTaskDef noneHoverTaskDef,
        ModResourceExports modResourceExports,
        ModShowParsedAstCommand modShowParsedAstCommand,
        ModShowParsedTokensCommand modShowParsedTokensCommand,
        ModShowPreAnalyzeAstCommand modShowPreAnalyzeAstCommand,
        ModShowScopeGraphCommand modShowScopeGraphCommand,
        ModShowScopeGraphAstCommand modShowScopeGraphAstCommand,
        ModShowAnalyzedAstCommand modShowAnalyzedAstCommand,
        ModTestStrategoTaskDef modTestStrategoTaskDef,
        ModAnalyze modAnalyze,
        Set<CommandDef<?>> commandDefs,
        Set<AutoCommandRequest<?>> autoCommandDefs
    ) {
        super(modParse, modTokenize, modCheckMulti, modCheckDeaggregator, modStyle, noneResolveTaskDef, noneHoverTaskDef, modResourceExports, modShowParsedAstCommand, modShowParsedTokensCommand, modShowPreAnalyzeAstCommand, modShowScopeGraphCommand, modShowScopeGraphAstCommand, modShowAnalyzedAstCommand, modTestStrategoTaskDef, modAnalyze, commandDefs, autoCommandDefs);
        this.modShowParsedAstCommand = modShowParsedAstCommand;
        this.modShowAnalyzedAstCommand = modShowAnalyzedAstCommand;
    }

    @Override public CliCommand getCliCommand() {
        final CliCommand parse = CliCommand.of("parse", "Parse a Mod source file and show its ATerm", modShowParsedAstCommand,
            CliParam.positional("file", 0, "file", "Mod source file")
        );
        final CliCommand analyze = CliCommand.of("analyze", "Parse and analyze a Mod source file and show its analyzed ATerm", modShowAnalyzedAstCommand,
            CliParam.positional("rootDirectory", 0, "rootDirectory", "Root directory to perform analysis in"),
            CliParam.positional("file", 1, "file", "Mod source file")
        );
        return CliCommand.of("Mod", parse, analyze);
    }
}
