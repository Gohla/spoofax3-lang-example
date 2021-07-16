package mb.mod.task;

import mb.aterm.common.TermToString;
import mb.pie.api.ExecContext;
import mb.pie.api.None;
import mb.pie.api.TaskDef;
import mb.resource.ResourceKey;
import mb.resource.hierarchical.ResourcePath;
import mb.resource.hierarchical.match.PathResourceMatcher;
import mb.resource.hierarchical.match.ResourceMatcher;
import mb.resource.hierarchical.match.path.ExtensionPathMatcher;
import mb.resource.hierarchical.match.path.NoHiddenPathMatcher;
import mb.resource.hierarchical.walk.PathResourceWalker;
import mb.resource.hierarchical.walk.ResourceWalker;
import mb.spoofax.core.language.command.CommandFeedback;
import mb.spoofax.core.language.command.ShowFeedback;
import mb.stratego.common.StrategoRuntime;
import mb.stratego.common.StrategoUtil;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.spoofax.interpreter.terms.IStrategoTerm;

import javax.inject.Inject;
import java.io.Serializable;
import java.util.Objects;

public class ModShowScopeGraph implements TaskDef<ModShowScopeGraph.Args, CommandFeedback> {
    public static class Args implements Serializable {
        public final ResourcePath project;
        public final ResourceKey file;

        public Args(ResourcePath project, ResourceKey file) {
            this.project = project;
            this.file = file;
        }

        @Override public boolean equals(@Nullable Object o) {
            if(this == o) return true;
            if(o == null || getClass() != o.getClass()) return false;
            final Args args = (Args)o;
            return project.equals(args.project) &&
                file.equals(args.file);
        }

        @Override public int hashCode() {
            return Objects.hash(project, file);
        }

        @Override public String toString() {
            return "ModShowScopeGraph$Args{" +
                "project=" + project +
                ", file=" + file +
                '}';
        }
    }

    private final ModParse parse;
    private final ModAnalyzeMulti analyze;
    private final ModGetStrategoRuntimeProvider strategoRuntimeProvider;

    @Inject public ModShowScopeGraph(
        ModParse parse,
        ModAnalyzeMulti analyze,
        ModGetStrategoRuntimeProvider strategoRuntimeProvider
    ) {
        this.parse = parse;
        this.analyze = analyze;
        this.strategoRuntimeProvider = strategoRuntimeProvider;
    }

    @Override public String getId() {
        return getClass().getName();
    }

    @Override public CommandFeedback exec(ExecContext context, Args args) {
        final ResourceWalker walker = new PathResourceWalker(new NoHiddenPathMatcher());
        final ResourceMatcher matcher = new PathResourceMatcher(new ExtensionPathMatcher("mod"));
        final ResourcePath project = args.project;
        final ResourceKey file = args.file;
        return context.require(analyze.createSingleFileOutputSupplier(new ModAnalyzeMulti.Input(project, parse.createRecoverableMultiAstSupplierFunction(walker, matcher)), file))
            .mapCatching(output -> {
                final StrategoRuntime strategoRuntime = context.require(strategoRuntimeProvider, None.instance).getValue().get().addContextObject(output.context);
                final IStrategoTerm inputTerm = StrategoUtil.createLegacyBuilderInputTerm(strategoRuntime.getTermFactory(), output.result.ast, file.asString(), project.asString());
                return TermToString.toString(strategoRuntime.invoke("stx--show-scopegraph", inputTerm));
            })
            .mapOrElse(text -> CommandFeedback.of(ShowFeedback.showText(text, "Scope graph for '" + file + "'")), e -> CommandFeedback.ofTryExtractMessagesFrom(e, file));
    }
}
