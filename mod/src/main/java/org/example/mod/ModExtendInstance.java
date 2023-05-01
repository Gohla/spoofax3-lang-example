package org.example.mod;

import mb.common.util.ListView;
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
    private final ModShowParsedTokensCommand modShowParsedTokensCommand;

    private final ModShowPreAnalyzeAstCommand modShowPreAnalyzeAstCommand;
    private final ModShowScopeGraphCommand modShowScopeGraphCommand;
    private final ModShowScopeGraphAstCommand modShowScopeGraphAstCommand;
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
        this.modShowParsedTokensCommand = modShowParsedTokensCommand;

        this.modShowPreAnalyzeAstCommand = modShowPreAnalyzeAstCommand;
        this.modShowScopeGraphCommand = modShowScopeGraphCommand;
        this.modShowScopeGraphAstCommand = modShowScopeGraphAstCommand;
        this.modShowAnalyzedAstCommand = modShowAnalyzedAstCommand;
    }

    @Override public CliCommand getCliCommand() {
        final CliParam fileParam = CliParam.positional("file", 0, "FILE", "Mod source file");
        final CliParam projectParam = CliParam.option("rootDirectory", ListView.of("--project"), false, "DIR", "Project directory to perform analysis in");

        final CliCommand parse = CliCommand.of("parse", "Parse a Mod source file and show its AST", modShowParsedAstCommand,
            fileParam
        );
        final CliCommand showParsedTokens = CliCommand.of("show-parsed-Tokens", "Parse a Mod source file and show its parsed tokens", modShowParsedTokensCommand,
            fileParam
        );

        final CliCommand showPreAnalyzedAst = CliCommand.of("show-pre-analyzed-ast", "Parse a Mod source file and show its pre-analyzed AST", modShowPreAnalyzeAstCommand,
            fileParam
        );
        final CliCommand showScopeGraph = CliCommand.of("show-scope-graph", "Parse and analyze a Mod source file and show its scope graph", modShowScopeGraphCommand,
            fileParam,
            projectParam
        );
        final CliCommand showScopeGraphAst = CliCommand.of("show-scope-graph-ast", "Parse and analyze a Mod source file and show its scope graph AST", modShowScopeGraphAstCommand,
            fileParam,
            projectParam
        );
        final CliCommand showAnalyzedAst = CliCommand.of("show-analyzed-ast", "Parse and analyze a Mod source file and show its analyzed AST", modShowAnalyzedAstCommand,
            fileParam,
            projectParam
        );
        return CliCommand.of("Mod",
            parse,
            showParsedTokens,

            showPreAnalyzedAst,
            showScopeGraph,
            showScopeGraphAst,
            showAnalyzedAst
        );
    }
}
