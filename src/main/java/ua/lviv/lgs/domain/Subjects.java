package ua.lviv.lgs.domain;

public enum Subjects {
	МАТЕМАТИКА("Математика"), ІНОЗЕМНА_МОВА("Іноземна_мова"), ІСТОРІЯ("Історія"), ПСИХОЛОГІЯ("Психологія"),
	МАРКЕТИНГ("Маркетинг"), МЕНЕДЖМЕНТ("Менеджмент"), ГЕОГРАФІЯ("Географія"), ЛІТЕРАТУРА("Література"),
	ІНФОРМАТИКА("Інформатика"), БІОЛОГІЯ("Біологія"), ХІМІЯ("Хімія");

	private String title;

	Subjects(String title) {
		this.title = title;
	}

	public String getTitle() {
		return title;
	}

	@Override
	public String toString() {
		return title;
	}
}
