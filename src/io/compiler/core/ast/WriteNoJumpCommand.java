package io.compiler.core.ast;

public class WriteNoJumpCommand extends Command{
	private String content;

	@Override
	public String generateTarget() {
		return "System.out.print("+content+");\n";
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public WriteNoJumpCommand(String content) {
		super();
		this.content = content;
	}

	public WriteNoJumpCommand() {
		super();
	}
}